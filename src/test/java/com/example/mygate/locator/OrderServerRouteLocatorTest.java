package com.example.mygate.locator;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class OrderServerRouteLocatorTest {

  @Autowired
  private WebTestClient webClient;

  @Test
  void addedHeaderRouteTest() throws Exception {
    //API Stubs
    stubFor(get(urlEqualTo("/gate_order_api/v1/order"))
        .willReturn(aResponse()
            .withBody("{\"headers\":{\"Authorization\":\"auth-test\"}}")
            .withHeader("Content-Type", "application/json")));

    webClient.get().uri("/gate_order_api/v1/order")
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.headers.Authorization").isEqualTo("auth-test");
  }

}