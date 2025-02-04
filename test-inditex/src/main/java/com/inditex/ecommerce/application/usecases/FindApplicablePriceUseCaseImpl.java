package com.inditex.ecommerce.application.usecases;

import com.inditex.ecommerce.domain.models.PriceDto;
import com.inditex.ecommerce.domain.ports.in.FindApplicablePriceUseCase;
import com.inditex.ecommerce.domain.ports.out.PriceRepositoryPort;
import com.inditex.ecommerce.shared.utils.DuplicatedOpsUtil;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
public class FindApplicablePriceUseCaseImpl implements FindApplicablePriceUseCase {

    private final PriceRepositoryPort priceRepositoryPort;

    @Override
    public Optional<PriceDto> findApplicablePrice(
            Long brandId, Long productId, LocalDateTime applicationDate) {

        DuplicatedOpsUtil.checkInputsNotNull(brandId, productId, applicationDate);

        return priceRepositoryPort.findApplicablePrice(brandId, productId, applicationDate);
    }

    @Override
    public Mono<PriceDto> rFindApplicablePrice(
            Long brandId, Long productId, LocalDateTime applicationDate) {

        DuplicatedOpsUtil.checkInputsNotNull(brandId, productId, applicationDate);

        return priceRepositoryPort.rFindApplicablePrice(brandId, productId, applicationDate);
    }
}
