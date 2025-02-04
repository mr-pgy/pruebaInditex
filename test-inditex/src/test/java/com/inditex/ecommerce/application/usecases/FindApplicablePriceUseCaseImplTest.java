package com.inditex.ecommerce.application.usecases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.inditex.ecommerce.domain.models.PriceDto;
import com.inditex.ecommerce.domain.ports.out.PriceRepositoryPort;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class FindApplicablePriceUseCaseImplTest {

  @Mock private PriceRepositoryPort priceRepositoryPort;

  @InjectMocks private FindApplicablePriceUseCaseImpl findApplicablePriceUseCase;

  @Test
  @DisplayName("Find applicable price with valid inputs")
  void findApplicablePriceWithValidInputs() {
    PriceDto priceDto = PriceDto.builder().build();
    when(priceRepositoryPort.findApplicablePrice(any(), any(), any()))
        .thenReturn(Optional.of(priceDto));

    Optional<PriceDto> result =
        findApplicablePriceUseCase.findApplicablePrice(1L, 35455L,
            LocalDateTime.of(2020, 6, 14, 10, 0, 0));

    assertTrue(result.isPresent());
    assertEquals(priceDto, result.get());
  }

  @Test
  @DisplayName("Find applicable price with null application date")
  void findApplicablePriceWithNullApplicationDate() {
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              findApplicablePriceUseCase.findApplicablePrice(1L, 35455L, null);
            });

    assertEquals(
        "brandId, productId and applicationDate cant be null", exception.getMessage());
  }

  @Test
  @DisplayName("Find applicable price with null product ID")
  void findApplicablePriceWithNullProductId() {
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              findApplicablePriceUseCase.findApplicablePrice(1L, null,
                  LocalDateTime.of(2020, 6, 14, 10, 0, 0));
            });

    assertEquals(
        "brandId, productId and applicationDate cant be null", exception.getMessage());
  }

  @Test
  @DisplayName("Find applicable price with null brand ID")
  void findApplicablePriceWithNullBrandId() {
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              findApplicablePriceUseCase.findApplicablePrice(null, 35455L,
                  LocalDateTime.of(2020, 6, 14, 10, 0, 0));
            });

    assertEquals(
        "brandId, productId and applicationDate cant be null", exception.getMessage());
  }

  @Test
  @DisplayName("Find applicable price when no price found")
  void findApplicablePriceWhenNoPriceFound() {
    when(priceRepositoryPort.findApplicablePrice(any(), any(), any())).thenReturn(Optional.empty());

    Optional<PriceDto> result =
        findApplicablePriceUseCase.findApplicablePrice(1L, 354555L,
            LocalDateTime.of(2022, 11, 15, 15, 0, 0));

    assertTrue(result.isEmpty());
  }

  @Test
  @DisplayName("Find applicable price with valid inputs")
  void findApplicablePriceReactiveWithValidInputs() {
    PriceDto priceDto = PriceDto.builder().build();
    when(priceRepositoryPort.rFindApplicablePrice(any(), any(), any()))
            .thenReturn(Mono.just(priceDto));

    Mono<PriceDto> result =
            findApplicablePriceUseCase.rFindApplicablePrice(1L, 35455L,
                    LocalDateTime.of(2020, 6, 14, 10, 0, 0));

    StepVerifier.create(result)
            .expectNext(priceDto)
            .verifyComplete();
  }

  @Test
  @DisplayName("Find applicable price with null application date")
  void findApplicablePriceReactiveWithNullApplicationDate() {
    IllegalArgumentException exception =
            assertThrows(
                    IllegalArgumentException.class,
                    () -> {
                      findApplicablePriceUseCase.rFindApplicablePrice(1L, 35455L, null);
                    });

    assertEquals(
            "brandId, productId and applicationDate cant be null", exception.getMessage());
  }

  @Test
  @DisplayName("Find applicable price with null product ID")
  void findApplicablePriceReactiveWithNullProductId() {
    IllegalArgumentException exception =
            assertThrows(
                    IllegalArgumentException.class,
                    () -> {
                      findApplicablePriceUseCase.rFindApplicablePrice(1L, null,
                              LocalDateTime.of(2020, 6, 14, 10, 0, 0));
                    });

    assertEquals(
            "brandId, productId and applicationDate cant be null", exception.getMessage());
  }

  @Test
  @DisplayName("Find applicable price with null brand ID")
  void findApplicablePriceReactiveWithNullBrandId() {
    IllegalArgumentException exception =
            assertThrows(
                    IllegalArgumentException.class,
                    () -> {
                      findApplicablePriceUseCase.rFindApplicablePrice(null, 35455L,
                              LocalDateTime.of(2020, 6, 14, 10, 0, 0));
                    });

    assertEquals(
            "brandId, productId and applicationDate cant be null", exception.getMessage());
  }

  @Test
  @DisplayName("Find applicable price when no price found")
  void findApplicablePriceReactiveWhenNoPriceFound() {
    when(priceRepositoryPort.rFindApplicablePrice(any(), any(), any())).thenReturn(Mono.empty());

    Mono<PriceDto> result =
            findApplicablePriceUseCase.rFindApplicablePrice(1L, 354555L,
                    LocalDateTime.of(2022, 11, 15, 15, 0, 0));

    StepVerifier.create(result)
            .expectNextCount(0)
            .verifyComplete();
  }
}
