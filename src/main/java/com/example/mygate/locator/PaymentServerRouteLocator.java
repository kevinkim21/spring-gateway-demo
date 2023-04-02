package com.example.mygate.locator;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class PaymentServerRouteLocator {

  public static final String PAYMENT_GATEWAY_PATH = "/gate_payment_api/v1/";
  public static final String PAYMENT_HOST = "http://localhost:8080/";

  @Bean
  public RouteLocator paymentLocator(RouteLocatorBuilder builder) {
    return builder.routes()
        .route("payment-api", r -> r.path(PAYMENT_GATEWAY_PATH+"/**")
            .filters(
                f -> f.addRequestHeader("Authorization", "auth-test")
                    .rewritePath(PAYMENT_GATEWAY_PATH+"(?<uri_path>.*)", "/${uri_path}"))
            .uri(PAYMENT_HOST))
        .build();
  }

}
