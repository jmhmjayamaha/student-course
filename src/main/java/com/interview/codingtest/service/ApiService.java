package com.interview.codingtest.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.interview.codingtest.dto.CourseApiResponse;

@Service
public class ApiService {

    private final WebClient webClient;

    public ApiService(WebClient.Builder webClientBuilder, @Value("${free.course.base.url}") String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    public CourseApiResponse callExternalApi(String id) {
        return this.webClient.get().uri("/adb3eb9d-392f-44f9-b308-d3a610d6e6b3?id=" + id).retrieve()
                .bodyToMono(CourseApiResponse.class)
                .block();
    }
}
