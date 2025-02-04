package com.inditex.ecommerce.infrastructure.controllers;

import com.inditex.ecommerce.domain.services.PriceService;
import com.inditex.ecommerce.domain.models.ErrorMessage;
import com.inditex.ecommerce.domain.models.PriceDto;
import com.inditex.ecommerce.infrastructure.exceptions.PriceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Tag(name = "Price", description = "The Price API")
@RestController
@RequestMapping("/price")
@RequiredArgsConstructor
public class PriceController {

  private final PriceService priceService;

  @Operation(
      summary = "Get Price",
      description =
          "Get price information. If two rates coincide in a range of dates, the one with the highest priority applies.")
  @ApiResponses(
      value = {
        @ApiResponse(
            description = "OK",
            responseCode = "200",
            content = {
              @Content(
                  schema = @Schema(implementation = PriceDto.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE)
            }),
        @ApiResponse(
            description = "Bad Request",
            responseCode = "400",
            content = {
              @Content(
                  schema = @Schema(implementation = ErrorMessage.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE)
            }),
        @ApiResponse(
            description = "Not Found",
            responseCode = "404",
            content = {
              @Content(
                  schema = @Schema(implementation = ErrorMessage.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE)
            })
      })
  @GetMapping(value = "/sync", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<PriceDto> findApplicablePrice(
      @Parameter(
              in = ParameterIn.QUERY,
              description = "Date to search for the applicable price",
              example = "2020-06-14-00.00.00")
          @RequestParam("date")
          @NotNull
          @Pattern(
              regexp = "\\d{4}-\\d{2}-\\d{2}-\\d{2}\\.\\d{2}\\.\\d{2}",
              message = "Invalid date format, format must be YYYY-MM-DD-HH.mm.ss")
          String date,
      @Parameter(in = ParameterIn.QUERY, description = "Product identifier", example = "123")
          @RequestParam("productId")
          @NotNull
          Long productId,
      @Parameter(in = ParameterIn.QUERY, description = "Brand identifier", example = "1")
          @RequestParam("brandId")
          @NotNull
          Long brandId) {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
    LocalDateTime applicationDate = LocalDateTime.parse(date, formatter);
    Optional<PriceDto> applicablePrice =
        priceService.findApplicablePrice(brandId, productId, applicationDate);

    return applicablePrice
        .map(ResponseEntity::ok)
        .orElseThrow(() -> new PriceNotFoundException("No applicable price found."));
  }

  @Operation(
      summary = "Get Price",
      description =
          "Get price information. If two rates coincide in a range of dates, the one with the highest priority applies.")
  @ApiResponses(
      value = {
        @ApiResponse(
            description = "OK",
            responseCode = "200",
            content = {
              @Content(
                  schema = @Schema(implementation = PriceDto.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE)
            }),
        @ApiResponse(
            description = "Bad Request",
            responseCode = "400",
            content = {
              @Content(
                  schema = @Schema(implementation = ErrorMessage.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE)
            }),
        @ApiResponse(
            description = "Not Found",
            responseCode = "404",
            content = {
              @Content(
                  schema = @Schema(implementation = ErrorMessage.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE)
            })
      })
  @GetMapping(value = "/async",produces = {MediaType.APPLICATION_JSON_VALUE})
  public Mono<ResponseEntity<PriceDto>> rFindApplicablePrice(
      @Parameter(
              in = ParameterIn.QUERY,
              description = "Date to search for the applicable price",
              example = "2020-06-14-00.00.00")
          @RequestParam("date")
          @NotNull
          @Pattern(
              regexp = "\\d{4}-\\d{2}-\\d{2}-\\d{2}\\.\\d{2}\\.\\d{2}",
              message = "Invalid date format, format must be YYYY-MM-DD-HH.mm.ss")
          String date,
      @Parameter(in = ParameterIn.QUERY, description = "Product identifier", example = "123")
          @RequestParam("productId")
          @NotNull
          Long productId,
      @Parameter(in = ParameterIn.QUERY, description = "Brand identifier", example = "1")
          @RequestParam("brandId")
          @NotNull
          Long brandId) {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
    LocalDateTime applicationDate = LocalDateTime.parse(date, formatter);
    Mono<PriceDto> applicablePrice =
        priceService.rFindApplicablePrice(brandId, productId, applicationDate);

    return applicablePrice.map(ResponseEntity::ok).switchIfEmpty(Mono.error(new PriceNotFoundException("No applicable price found.")));
  }

}
