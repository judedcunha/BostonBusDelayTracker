package com.jude.bostonbusdelay.service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import com.jude.bostonbusdelay.model.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MBTAService {
    private final WebClient mbtaWebClient;

    public MBTAService(WebClient mbtaWebClient) {
        this.mbtaWebClient = mbtaWebClient;
    }

    @Cacheable(value = "delays", unless = "#result == null || #result.isEmpty()")
    public List<DelayDTO> getBusDelays() {
        String requestUrl = "/alerts?filter[route_type]=3"; // Fetch all bus alerts
        System.out.println("Request URL: " + requestUrl);

        try {
            MBTAAlertResponse response = mbtaWebClient.get()
                    .uri(requestUrl)
                    .retrieve()
                    .bodyToMono(MBTAAlertResponse.class)
                    .block();

            // Filter alerts with the DELAY effect
            return response.getData().stream()
                    .filter(alert -> "DELAY".equalsIgnoreCase(alert.getAttributes().getEffect()))
                    .map(alert -> new DelayDTO(
                            alert.getId(),
                            alert.getAttributes().getHeader(),
                            alert.getAttributes().getDescription()
                    ))
                    .collect(Collectors.toList());
        } catch (WebClientResponseException ex) {
            System.err.println("MBTA API Error: " + ex.getRawStatusCode() + " - " + ex.getResponseBodyAsString());
            throw ex; // Re-throw the exception to propagate it
        }
    }
}