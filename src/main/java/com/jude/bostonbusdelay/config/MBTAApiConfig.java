package com.jude.bostonbusdelay.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class MBTAApiConfig {
    
    @Value("${mbta.api.url}")
    private String mbtaApiUrl;

    @Value("${mbta.api.key}")
    private String apiKey;

    @Bean
    public WebClient mbtaWebClient() {
        return WebClient.builder()
                .baseUrl(mbtaApiUrl)
                .defaultHeader("apiKey", apiKey)
                .build();
    }
}