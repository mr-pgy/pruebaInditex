package com.inditex.ecommerce.infrastructure.entities;

import com.inditex.ecommerce.domain.models.CurrencyEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "prices")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Schema(description = "Entity representing a price in the system.")
public class PriceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Unique identifier of the price.", example = "1")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "brand_id")
  @Schema(description = "Brand associated with the price.")
  private BrandEntity brand;

  @ManyToOne
  @JoinColumn(name = "product_id")
  @Schema(description = "Product associated with the price.")
  private ProductEntity product;

  @NotNull
  @Schema(description = "Start date of the price validity.", example = "2023-01-01T00:00:00")
  private LocalDateTime startDate;

  @NotNull
  @Schema(description = "End date of the price validity.", example = "2023-12-31T23:59:59")
  private LocalDateTime endDate;

  @NotNull
  @Schema(description = "Price list identifier.", example = "1")
  private Long priceList;

  @NotNull
  @Schema(description = "Priority of the price.", example = "1")
  private Integer priority;

  @NotNull
  @Schema(description = "Price value.", example = "100.00")
  private BigDecimal price;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Schema(description = "Currency of the price.", example = "USD")
  private CurrencyEnum currency;

  @LastModifiedDate
  @Schema(description = "Last update timestamp.", example = "2023-10-01T12:00:00")
  private LocalDateTime lastUpdate;

  @LastModifiedBy
  @Schema(description = "User who last updated the price.", example = "admin")
  private String lastUpdateBy = "admin";

  @PreUpdate
  public void preUpdate() {
    this.lastUpdate = LocalDateTime.now();
    this.lastUpdateBy = "admin";
  }
}
