package com.inditex.ecommerce.infrastructure.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import lombok.*;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "brands")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entity representing a brand in the system.")
public class BrandEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Unique identifier of the brand.", example = "1")
  private Long id;

  @Schema(description = "Name of the brand.", example = "BrandName")
  private String name;

  @Schema(description = "Description of the brand.", example = "ABC Brand")
  private String description;

  @OneToMany(mappedBy = "brand")
  @Schema(description = "List of prices associated with the brand.")
  private List<PriceEntity> prices;

  @LastModifiedDate
  @Schema(description = "Last update timestamp.", example = "2023-10-01T12:00:00")
  private LocalDateTime lastUpdate;

  @LastModifiedBy
  @Schema(description = "User who last updated the brand.", example = "admin")
  private String lastUpdateBy = "admin";

  @PreUpdate
  public void preUpdate() {
    this.lastUpdate = LocalDateTime.now();
    this.lastUpdateBy = "admin";
  }
}
