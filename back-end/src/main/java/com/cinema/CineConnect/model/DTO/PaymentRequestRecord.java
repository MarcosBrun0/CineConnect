package com.cinema.CineConnect.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Map;

public record PaymentRequestRecord(
                String token,
                @JsonProperty("issuer_id") String issuerId,
                @JsonProperty("payment_method_id") String paymentMethodId,
                @JsonProperty("transaction_amount") BigDecimal transactionAmount,
                Integer installments,
                String description,
                Map<String, Object> payer,
                String userId) {
}
