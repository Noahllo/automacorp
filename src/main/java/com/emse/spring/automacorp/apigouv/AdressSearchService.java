package com.emse.spring.automacorp.apigouv;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdressSearchService {

    private final RestTemplate restTemplate;

    // Constructor
    public AdressSearchService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.rootUri("https://example.com").build();
    }


    // Method to return a list of ApiGouvAdress
    public List<ApiGouvAdressDto> searchAdresses(List<String> keys) {
        String params = String.join("+", keys);
        String uri = UriComponentsBuilder.fromUriString("/search")
                .queryParam("q", params)
                .queryParam("limit", 15)
                .build()
                .toUriString();

        ApiGouvResponseDto response = restTemplate.getForObject(uri, ApiGouvResponseDto.class);

        return response != null ?
                response.features().stream()
                        .map(ApiGouvFeatureDto::properties)
                        .collect(Collectors.toList())
                : Collections.emptyList();
    }
}

