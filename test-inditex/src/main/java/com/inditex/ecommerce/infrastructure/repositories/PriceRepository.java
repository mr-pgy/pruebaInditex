package com.inditex.ecommerce.infrastructure.repositories;

import com.inditex.ecommerce.infrastructure.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    /**
     * Finds the applicable price for product, brand and date.
     *
     * @param brandId         brand identifier
     * @param productId       product identifier
     * @param applicationDate date of the price
     * @return {@link Mono<PriceEntity>} The applicable price for the given product
     */
    @Query(
            "SELECT p FROM PriceEntity p WHERE p.product.id = :productId AND p.brand.id = :brandId "
                    + "AND :applicationDate BETWEEN p.startDate AND p.endDate "
                    + "ORDER BY p.priority DESC LIMIT 1 ")
    Optional<PriceEntity> findApplicablePrice(
            @Param("brandId") Long brandId,
            @Param("productId") Long productId,
            @Param("applicationDate")
            LocalDateTime applicationDate);

}
