package com.example.notarycrm.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    public RestTemplateConfig() {
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}