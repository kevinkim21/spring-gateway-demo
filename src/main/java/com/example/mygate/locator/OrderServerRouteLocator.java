package com.example.mygate.locator;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OrderServerRouteLocator {

  public static final String ORDER_GATEWAY_PATH = "/gate_order_api/v1";
  public static final String ORDER_HOST = "http://localhost:8080/";

  @Bean
  public RouteLocator orderLocator(RouteLocatorBuilder builder) {
    return builder.routes()
        .route("order-api", r -> r.path(ORDER_GATEWAY_PATH+"/**")
            .filters(
                f -> f.addRequestHeader("Authorization", "auth-test")
                    .rewritePath(ORDER_GATEWAY_PATH+"(?<uri_path>.*)", "/${uri_path}"))
            .uri(ORDER_HOST))
        .build();
  }
}
