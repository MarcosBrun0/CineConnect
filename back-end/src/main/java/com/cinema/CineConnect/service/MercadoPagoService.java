package com.cinema.CineConnect.service;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.order.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.*;

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

    public Map<String, String> createPayment() {
        UUID uuid = UUID.randomUUID();
        Map<String, String> customHeaders = new HashMap<>();
        customHeaders.put("x-idempotency-key",uuid.toString());

        MPRequestOptions requestOptions = MPRequestOptions.builder()
                .customHeaders(customHeaders)
                .build();

        MercadoPagoConfig.setAccessToken(accessToken);

        PaymentClient client = new PaymentClient();

        PaymentCreateRequest paymentCreateRequest =
                PaymentCreateRequest.builder()
                        .transactionAmount(new BigDecimal("100"))
                        .paymentMethodId("pix")
                        .payer(
                                PaymentPayerRequest.builder()
                                        .email("PAYER_EMAIL_HERE")
                                        .build())
                        .build();

        try {
            Order order = client.create(paymentCreateRequest, requestOptions);

            // Converte a resposta em Map<String, String> usando Jackson
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> result = new HashMap<>();

            // Adiciona apenas campos simples como string
            result.put("id", response.getId() != null ? response.getId().toString() : null);
            result.put("status", response.getStatus());
            result.put("paymentMethodId", response.getPaymentMethodId());
            result.put("payerEmail", response.getPayer() != null ? response.getPayer().getEmail() : null);

            return result;
            return (client.create(paymentCreateRequest, requestOptions));
        } catch (MPException | MPApiException e) {
            throw new RuntimeException(e);
        }


    }
}
