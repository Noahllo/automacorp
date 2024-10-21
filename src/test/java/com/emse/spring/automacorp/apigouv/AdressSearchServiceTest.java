package com.emse.spring.automacorp.apigouv;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestClientTest(AdressSearchService.class) //
class AdressSearchServiceTest {
    @Autowired
    private AdressSearchService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockRestServiceServer server; //

    @Test
    void shouldFindAdresses() throws JsonProcessingException {
        // Arrange
        ApiGouvResponseDto expectedResponse = simulateApiResponse();

        String expectedUrl = UriComponentsBuilder
                .fromUriString("/search")
                .queryParam("q", "cours+fauriel")
                .queryParam("limit", 15)
                .build()
                .toUriString();

        this.server
                .expect(MockRestRequestMatchers.requestTo(expectedUrl))
                .andRespond(
                        MockRestResponseCreators.withSuccess(
                                objectMapper.writeValueAsString(expectedResponse),
                                MediaType.APPLICATION_JSON
                        )
                );

        // Act
        List<ApiGouvAdressDto> adresses = this.service.searchAdresses(List.of("cours", "fauriel"));

        // Assert
        Assertions
                .assertThat(adresses)
                .hasSize(1)
                .extracting(ApiGouvAdressDto::city)
                .contains("Saint Etienne");
    }

    private ApiGouvResponseDto simulateApiResponse() {
        ApiGouvAdressDto adress = new ApiGouvAdressDto(
                "ad1",
                "Cours Fauriel 42100 Saint-Étienne",
                "2",
                0.98,
                "42100",
                "42218",
                "Saint Etienne",
                "context",
                "type",
                0.0,
                0.0
        );

        ApiGouvFeatureDto feature = new ApiGouvFeatureDto("type", adress);
        return new ApiGouvResponseDto("v1", "cours+fauriel", 15, List.of(feature));
    }
}