package com.cinema.CineConnect.service;

import com.cinema.CineConnect.model.DTO.ProductRecord;
import com.cinema.CineConnect.model.FoodProduct;
import com.cinema.CineConnect.model.Product;
import com.cinema.CineConnect.model.Ticket;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class StripeService {

    @Value("${STRIPE_SECRET_KEY}")
    private String stripeSecretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
    }

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

    public String createCheckoutSession(List<ProductRecord> cart, String successUrl, String cancelUrl) {
        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();

        for (ProductRecord productRecord : cart) {
            Product product = processProduct(productRecord);

            // Item principal
            lineItems.add(buildLineItem(product));

            // Add-ons se existirem
            if (product instanceof FoodProduct foodProduct) {
                if (foodProduct.getAddOns() != null) {
                    for (FoodProduct addOn : foodProduct.getAddOns()) {
                        lineItems.add(buildLineItem(addOn, "Add-on: "));
                    }
                }
            }
        }

        SessionCreateParams params = SessionCreateParams.builder()
                .addAllLineItem(lineItems)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(successUrl)
                .setCancelUrl(cancelUrl)
                .build();

        try {
            Session session = Session.create(params);
            return session.getUrl(); // URL do Stripe Checkout
        } catch (StripeException e) {
            throw new RuntimeException("Erro ao criar Stripe Checkout Session", e);
        }
    }

    private SessionCreateParams.LineItem buildLineItem(Product product) {
        return buildLineItem(product, "");
    }

    private SessionCreateParams.LineItem buildLineItem(Product product, String prefixName) {
        return SessionCreateParams.LineItem.builder()
                .setPriceData(
                        SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("brl")
                                .setUnitAmount(product.getProductPrice().multiply(new BigDecimal(100)).longValue())
                                .setProductData(
                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                .setName(prefixName + product.getProductName())
                                                .build()
                                )
                                .build()
                )
                .setQuantity(1L)
                .build();
    }
}
