package com.inditex.ecommerce.infrastructure.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "products")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entity representing a product in the system.")
public class ProductEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Unique identifier of the product.", example = "1")
  private Long id;

  @Schema(description = "Name of the product.", example = "Laptop")
  private String name;

  @Schema(description = "Description of the product.", example = "A gaming laptop.")
  private String description;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  @Schema(description = "List of prices associated with the product.")
  private List<PriceEntity> prices;

  @LastModifiedDate
  @Schema(description = "Last update timestamp.", example = "2023-10-01T12:00:00")
  private LocalDateTime lastUpdate;

  @LastModifiedBy
  @Schema(description = "User who last updated the product.", example = "admin")
  private String lastUpdateBy = "admin";

  @PreUpdate
  public void preUpdate() {
    this.lastUpdate = LocalDateTime.now();
    this.lastUpdateBy = "admin";
  }
}
