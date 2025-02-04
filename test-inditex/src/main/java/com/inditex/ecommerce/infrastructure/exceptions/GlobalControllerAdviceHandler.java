package com.inditex.ecommerce.infrastructure.exceptions;

import com.inditex.ecommerce.domain.models.ErrorMessage;
import java.time.format.DateTimeParseException;
import java.util.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalControllerAdviceHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(PriceNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<ErrorMessage> handlePriceNotFoundException(PriceNotFoundException ex) {
    ErrorMessage message = new ErrorMessage(ex.getMessage(), HttpStatus.NOT_FOUND);

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
  }

  @ExceptionHandler(DateTimeParseException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorMessage> handleDateTimeParseException(DateTimeParseException ex) {
    ErrorMessage message =
        new ErrorMessage(
            "Invalid date format, format must be YYYY-MM-DD-HH.mm.ss", HttpStatus.BAD_REQUEST);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
  }

  @Override
  protected ResponseEntity<Object> handleHandlerMethodValidationException(
      HandlerMethodValidationException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    List<String> errors =
        ex.getAllErrors().stream()
            .map(
                error ->
                    Optional.ofNullable(error.getDefaultMessage()).orElse("Validation failed."))
            .toList();

    String firstError = errors.stream().findFirst().orElse("Validation failed");
    ErrorMessage message = new ErrorMessage(firstError, HttpStatus.BAD_REQUEST);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
  }
}
