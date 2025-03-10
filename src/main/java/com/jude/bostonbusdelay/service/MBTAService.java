package com.jude.bostonbusdelay.service;
import com.jude.bostonbusdelay.model.*;
import com.jude.bostonbusdelay.repository.SearchHistoryRepository;

import io.micrometer.common.lang.Nullable;
import reactor.core.publisher.Mono;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class MBTAService {
    private static final Logger logger = LoggerFactory.getLogger(MBTAService.class);
    
    private final WebClient mbtaWebClient;
    private final SearchHistoryRepository searchHistoryRepository;

    public MBTAService(WebClient mbtaWebClient, SearchHistoryRepository searchHistoryRepository) {
        this.mbtaWebClient = mbtaWebClient;
        this.searchHistoryRepository = searchHistoryRepository;
    }

    public List<DelayDTO> getBusDelays(@Nullable String routeId) {
        try {
            // If routeId is null, fetch delays for all routes
            String requestUrl = "/alerts?filter[route_type]=3" + 
                              (routeId != null ? "&filter[route]=" + routeId : "");
            
            MBTAAlertResponse response = mbtaWebClient.get()
                    .uri(requestUrl)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, clientResponse -> {
                        logger.error("MBTA API Error: {}", clientResponse.statusCode());
                        return Mono.error(new RuntimeException("MBTA API Error"));
                    })
                    .bodyToMono(MBTAAlertResponse.class)
                    .block();
    
            if (response == null || response.getData() == null) {
                logger.warn("Received null response from MBTA API");
                return Collections.emptyList();
            }
    
            List<DelayDTO> delays = response.getData().stream()
                    .filter(alert -> "DELAY".equalsIgnoreCase(alert.getAttributes().getEffect()))
                    .map(alert -> new DelayDTO(
                            alert.getId(),
                            alert.getAttributes().getHeader(),
                            alert.getAttributes().getDescription()))
                    .collect(Collectors.toList());
    
            // Save search history
            SearchHistory history = new SearchHistory();
            history.setRouteId(routeId != null ? routeId : "all");
            history.setSearchTime(LocalDateTime.now());
            history.setDelayCount(delays.size());
            
            searchHistoryRepository.save(history);
            
            return delays;
            
        } catch (Exception e) {
            logger.error("Error fetching delays", e);
            return Collections.emptyList();
        }
    }
}