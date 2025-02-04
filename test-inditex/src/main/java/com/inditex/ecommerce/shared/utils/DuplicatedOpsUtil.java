package com.inditex.ecommerce.shared.utils;

import java.time.LocalDateTime;
import java.util.Objects;

public class DuplicatedOpsUtil {


    public static void checkInputsNotNull(Long brandId, Long productId, LocalDateTime applicationDate) {
        if (Objects.isNull(brandId) || Objects.isNull(productId) || Objects.isNull(applicationDate)) {
            throw new IllegalArgumentException(
                    "brandId, productId and applicationDate cant be null");
        }
    }
}
