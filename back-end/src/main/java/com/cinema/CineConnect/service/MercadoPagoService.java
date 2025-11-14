package com.cinema.CineConnect.service;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class MercadoPagoService {

    private PreferenceClient client;

    @Value("${PROD_ACCESS_TOKEN}")
    private String accessToken;

    @PostConstruct
    public void init() {
        // Set your Mercado Pago access token
        MercadoPagoConfig.setAccessToken(accessToken);
        client = new PreferenceClient();
    }
    /**
     * Creates a preference in Mercado Pago and returns the preference ID
     */
    public String createPreference() {
        var title="produto";
        BigDecimal price= BigDecimal.valueOf(10);
        List<PreferenceItemRequest> items = new ArrayList<>();
        items.add(
                PreferenceItemRequest.builder()
                        .title(title)
                        .quantity(1)
                        .unitPrice(price)
                        .build()
        );

        PreferenceRequest request = PreferenceRequest.builder()
                //.purpose("wallet_purchase") // optional: remove to allow guest payments
                .items(items)
                .build();

        try {
            return client.create(request).getId();
        } catch (MPApiException | MPException e) {
            throw new RuntimeException("Failed to create Mercado Pago preference", e);
        }
    }

}
