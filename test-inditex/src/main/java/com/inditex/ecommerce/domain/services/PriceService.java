package com.inditex.ecommerce.domain.services;

import com.inditex.ecommerce.domain.models.PriceDto;
import com.inditex.ecommerce.domain.ports.in.FindApplicablePriceUseCase;
import java.time.LocalDateTime;
import java.util.Optional;

import com.inditex.ecommerce.infrastructure.exceptions.PriceNotFoundException;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;


@AllArgsConstructor
public class PriceService {

  private final FindApplicablePriceUseCase findApplicablePriceUseCase;

  /**
   * Finds the applicable price for product, brand and date.
   *
   * @param brandId brand identifier
   * @param productId product identifier
   * @param applicationDate date of the price
   * @return {@link Optional<PriceDto>} The applicable price for the given product, if not found
   *     result, returns Optional.empty()
   */
  public Optional<PriceDto> findApplicablePrice(
          Long brandId, Long productId, LocalDateTime applicationDate) {

    return findApplicablePriceUseCase.findApplicablePrice(brandId, productId, applicationDate);
  }


  /**
   * Finds the applicable price for product, brand and date.
   *
   * @param brandId brand identifier
   * @param productId product identifier
   * @param applicationDate date of the price
   * @return {@link Mono<PriceDto>} The applicable price for the given product, if not found
   *     result, returns Mono.empty()
   */
  public Mono<PriceDto> rFindApplicablePrice(
          Long brandId, Long productId, LocalDateTime applicationDate) {

    return findApplicablePriceUseCase.rFindApplicablePrice(brandId, productId, applicationDate);
  }
}
