package com.inditex.ecommerce.infrastructure.repositories;

import com.inditex.ecommerce.infrastructure.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {}
