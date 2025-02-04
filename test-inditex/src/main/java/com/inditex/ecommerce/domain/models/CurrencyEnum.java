package com.inditex.ecommerce.domain.models;

import io.swagger.v3.oas.annotations.media.Schema;

/** Enum currencies. **/
@Schema(description = "Currencies")
public enum CurrencyEnum {
  @Schema(description = "Euro", example = "EUR")
  EUR,

  @Schema(description = "USD", example = "USD")
  USD
}
