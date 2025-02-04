package com.inditex.ecommerce.infrastructure.config;

import com.inditex.ecommerce.domain.services.PriceService;
import com.inditex.ecommerce.application.usecases.FindApplicablePriceUseCaseImpl;
import com.inditex.ecommerce.domain.ports.in.FindApplicablePriceUseCase;
import com.inditex.ecommerce.domain.ports.out.PriceRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanPriceConfig {

  /**
   * Creates a new PriceService instance.
   *
   * @param findApplicablePriceUseCase The use case to find the applicable price
   * @return {@link PriceService} The price service
   */
  @Bean
  public PriceService priceService(FindApplicablePriceUseCase findApplicablePriceUseCase) {
    return new PriceService(findApplicablePriceUseCase);
  }

  /**
   * Creates a new FindApplicablePriceUseCase instance.
   *
   * @param priceRepositoryPort The price repository port
   * @return {@link FindApplicablePriceUseCase} The use case to find the applicable price
   */
  @Bean
  public FindApplicablePriceUseCase findApplicablePriceUseCase(
      PriceRepositoryPort priceRepositoryPort) {
    return new FindApplicablePriceUseCaseImpl(priceRepositoryPort);
  }
}
