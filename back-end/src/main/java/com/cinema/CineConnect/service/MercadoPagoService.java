package com.cinema.CineConnect.service;

import com.cinema.CineConnect.model.DTO.ProductRecord;
import com.cinema.CineConnect.model.FoodProduct;
import com.cinema.CineConnect.model.Product;
import com.cinema.CineConnect.model.Ticket;
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
        MercadoPagoConfig.setAccessToken(accessToken);
        client = new PreferenceClient();
    }

    // convert DTO to Domain Object
    public Product processProduct(ProductRecord productRecord) {
        return switch (productRecord.type()) {
            case "TICKET" -> new Ticket(
                    productRecord.productId(),
                    productRecord.sessionId(),
                    productRecord.name(),
                    productRecord.type(),
                    productRecord.price()
            );
            case "FOOD" -> new FoodProduct(productRecord);
            default -> throw new RuntimeException("Invalid product type " + productRecord.type());
        };
    }

    public String createPreference(List<ProductRecord> cart) {
        List<PreferenceItemRequest> items = new ArrayList<>();

        for (ProductRecord productRecord : cart) {
            Product currentProduct = processProduct(productRecord);

            // 1. Add the main item
            items.add(PreferenceItemRequest.builder()
                    .title(currentProduct.getProductName())
                    .quantity(1)
                    .unitPrice(currentProduct.getProductPrice())
                    .build());

            // 2. CHECK FOR ADD-ONS
            if (currentProduct instanceof FoodProduct foodProduct) {
                List<FoodProduct> addOns = foodProduct.getAddOns();
                if (addOns != null && !addOns.isEmpty()) {
                    for (FoodProduct addOn : addOns) {
                        items.add(PreferenceItemRequest.builder()
                                .title("Add-on: " + addOn.getProductName()) // Indication it's an add-on
                                .quantity(1)
                                .unitPrice(addOn.getProductPrice())
                                .build());
                    }
                }
            }
        }

        PreferenceRequest request = PreferenceRequest.builder()
                .items(items)
                .build();

        try {
            return client.create(request).getId();
        } catch (MPApiException | MPException e) {
            throw new RuntimeException("Failed to create Mercado Pago preference", e);
        }
    }

    // Updated to accept Email
    public Map<String, String> createPayment(String payerEmail) {
        UUID uuid = UUID.randomUUID();

        MPRequestOptions requestOptions = MPRequestOptions.builder()
                .customHeaders(Map.of("x-idempotency-key", uuid.toString()))
                .build();

        PaymentClient paymentClient = new PaymentClient();

        PaymentCreateRequest paymentCreateRequest =
                PaymentCreateRequest.builder()
                        .transactionAmount(new BigDecimal("100"))
                        .description("Cinema Ticket Purchase")
                        .paymentMethodId("pix")
                        .payer(PaymentPayerRequest.builder().email(payerEmail).build())
                        .build();

        try {
            var response = paymentClient.create(paymentCreateRequest, requestOptions);

            Map<String, String> result = new HashMap<>();
            result.put("id", response.getId() != null ? response.getId().toString() : null);
            result.put("status", response.getStatus());
            result.put("qr_code", response.getPointOfInteraction().getTransactionData().getQrCode()); // Useful for PIX
            result.put("qr_code_base64", response.getPointOfInteraction().getTransactionData().getQrCodeBase64()); // Useful for PIX
            result.put("paymentMethodId", response.getPaymentMethodId());

            return result;

        } catch (MPException | MPApiException e) {
            throw new RuntimeException("Error processing payment", e);
        }
    }
}