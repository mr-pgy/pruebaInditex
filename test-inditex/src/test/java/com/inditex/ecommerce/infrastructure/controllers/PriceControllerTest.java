package com.inditex.ecommerce.infrastructure.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.inditex.ecommerce.domain.services.PriceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureWebTestClient
class PriceControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired
  private WebTestClient webTestClient;

  @Autowired private PriceService priceService;

  @Test
  @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
  void test1() throws Exception {
    performTest("2020-06-14-10.00.00", "35455", "1", 200);
  }

  @Test
  @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
  void test2() throws Exception {
    performTest("2020-06-14-16.00.00", "35455", "1", 200);
  }

  @Test
  @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
  void test3() throws Exception {
    performTest("2020-06-14-21.00.00", "35455", "1", 200);
  }

  @Test
  @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)")
  void test4() throws Exception {
    performTest("2020-06-15-10.00.00", "35455", "1", 200);
  }

  @Test
  @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)")
  void test5() throws Exception {
    performTest("2020-06-16-21.00.00", "35455", "1", 200);
  }

  @Test
  @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
  void test1R() throws Exception {
    performReactiveTest("2020-06-14-10.00.00", "35455", "1", 200);
  }

  @Test
  @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
  void test2R() throws Exception {
    performReactiveTest("2020-06-14-16.00.00", "35455", "1", 200);
  }

  @Test
  @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
  void test3R() throws Exception {
    performReactiveTest("2020-06-14-21.00.00", "35455", "1", 200);
  }

  @Test
  @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)")
  void test4R() throws Exception {
    performReactiveTest("2020-06-15-10.00.00", "35455", "1", 200);
  }

  @Test
  @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)")
  void test5R() throws Exception {
    performReactiveTest("2020-06-16-21.00.00", "35455", "1", 200);
  }


  @Test
  void findApplicablePriceWhenNoPriceFound() throws Exception {
    mockMvc
        .perform(
            get("/price/sync")
                .param("date", "2022-11-15-15.00.00")
                .param("productId", "354555")
                .param("brandId", "1")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  void findApplicablePriceWhenBadRequest() throws Exception {
    mockMvc
        .perform(
            get("/price/async")
                .param("productId", "35455")
                .param("brandId", "1")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  void findApplicablePriceRWhenNoPriceFound() throws Exception {
    webTestClient.get()
            .uri(uriBuilder -> uriBuilder.path("/price/async")
                    .queryParam("date", "2022-11-15-15.00.00")
                    .queryParam("productId", "354555") // ID de producto que no existe
                    .queryParam("brandId", "1")
                    .build())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isNotFound();
  }

  @Test
  void findApplicablePriceRWhenBadRequest() throws Exception {
    webTestClient.get()
            .uri(uriBuilder -> uriBuilder.path("/price/async")
                    .queryParam("productId", "354555") // ID de producto que no existe
                    .queryParam("brandId", "1")
                    .build())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isBadRequest();
  }

  private void performTest(String date, String productId, String brandId, int expectedStatus)
      throws Exception {
    mockMvc
        .perform(
            get("/price/sync")
                .param("date", date)
                .param("productId", productId)
                .param("brandId", brandId)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(expectedStatus))
        .andExpect(jsonPath("$.productId").value(productId));
  }

  private void performReactiveTest(String date, String productId, String brandId, int expectedStatus)
          throws Exception {

    webTestClient.get()
            .uri(uriBuilder -> uriBuilder.path("/price/async")
                    .queryParam("date", date)
                    .queryParam("productId", productId)
                    .queryParam("brandId", brandId)
                    .build())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isEqualTo(HttpStatus.valueOf(expectedStatus))
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.productId").isEqualTo(productId);
  }
}
