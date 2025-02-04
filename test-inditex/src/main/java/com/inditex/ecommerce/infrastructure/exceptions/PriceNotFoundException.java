package com.inditex.ecommerce.infrastructure.exceptions;

public class PriceNotFoundException extends RuntimeException {

  public PriceNotFoundException(String message) {
    super(message);
  }
}
