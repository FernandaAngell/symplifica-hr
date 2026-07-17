package com.symplifica.backend.nominatim.service;

import com.symplifica.backend.nominatim.client.NominatimClient;
import com.symplifica.backend.nominatim.dto.NominatimResponse;
import org.springframework.stereotype.Service;

@Service
public class NominatimService {

    private final NominatimClient client;

    public NominatimService(NominatimClient client) {
        this.client = client;
    }

    public NominatimResponse searchCity(String city) {
        return client.searchCity(city);
    }
}