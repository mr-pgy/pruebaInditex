package com.inditex.ecommerce.infrastructure.adapters;

import com.inditex.ecommerce.domain.models.PriceDto;
import com.inditex.ecommerce.domain.ports.out.PriceRepositoryPort;
import com.inditex.ecommerce.infrastructure.mappers.PriceMapper;
import com.inditex.ecommerce.infrastructure.repositories.PriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    @Override
    public Optional<PriceDto> findApplicablePrice(
            Long brandId, Long productId, LocalDateTime applicationDate) {

        return priceRepository
                .findApplicablePrice(brandId, productId, applicationDate)
                .map(priceMapper::toPriceDto);
    }

    @Override
    public Mono<PriceDto> rFindApplicablePrice(
            Long brandId, Long productId, LocalDateTime applicationDate) {

        Optional<PriceDto> priceDtoOptional = priceRepository
                .findApplicablePrice(brandId, productId, applicationDate)
                .map(priceMapper::toPriceDto);
        return Mono.justOrEmpty(priceDtoOptional);
    }
}
