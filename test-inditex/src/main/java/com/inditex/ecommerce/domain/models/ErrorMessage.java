package com.inditex.ecommerce.domain.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/** Represents an error message with a status code. */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ErrorMessage {

  @Schema(
      description = "Error message describing the issue",
      example = "No applicable price found.")
  private String message;

  @Schema(description = "HTTP status code of the error", example = "404 NOT_FOUND")
  private HttpStatus status;
}
