package com.symplifica.backend.nominatim.client;

import com.symplifica.backend.nominatim.dto.NominatimResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class NominatimClient {

    private final WebClient webClient;

    public NominatimClient(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("https://nominatim.openstreetmap.org")
                .build();
    }

    public NominatimResponse searchCity(String city) {

        List<NominatimResponse> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search")
                        .queryParam("q", city)
                        .queryParam("format", "jsonv2")
                        .queryParam("limit", 1)
                        .build())
                .header("User-Agent", "symplifica-hr")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<NominatimResponse>>() {})
                .block();

        if (response == null || response.isEmpty()) {
            return null;
        }

        return response.getFirst();
    }
}