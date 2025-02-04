package com.inditex.ecommerce.domain.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * Price dto.
 */
@Getter
@Setter
@Builder
public class PriceDto {

    @Schema(
            name = "productId",
            description = "Product Identifier",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "35455")
    private Long productId;

    @Schema(
            name = "brandId",
            description = "Brand Identifier",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "1")
    private Long brandId;

    @Schema(
            name = "startDate",
            description = "Start date for price.",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "2020-06-14-00.00.00")
    private LocalDateTime startDate;

    @Schema(
            name = "endDate",
            description = "End date for price",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "2020-12-31-23.59.59 ")
    private LocalDateTime endDate;

    @Schema(
            name = "priceList",
            description = "PriceList Identifier",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "1")
    private Long priceList;

    @Schema(
            name = "price",
            description = "Final Sale price",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "35.50")
    private BigDecimal price;

    @Schema(
            name = "currency",
            description = "ISO currency",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "EUR")
    private CurrencyEnum currency;
}
