package com.tmdt.m4s3b6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofSeconds(2))   // Kết nối timeout
                .setReadTimeout(Duration.ofSeconds(3))      // Đọc dữ liệu timeout
                .build();
    }
}