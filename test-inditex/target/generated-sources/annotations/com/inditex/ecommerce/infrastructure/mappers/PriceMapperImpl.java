package com.inditex.ecommerce.infrastructure.mappers;

import com.inditex.ecommerce.domain.models.PriceDto;
import com.inditex.ecommerce.infrastructure.entities.BrandEntity;
import com.inditex.ecommerce.infrastructure.entities.PriceEntity;
import com.inditex.ecommerce.infrastructure.entities.ProductEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-04T15:41:50+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class PriceMapperImpl implements PriceMapper {

    @Override
    public PriceDto toPriceDto(PriceEntity priceEntity) {
        if ( priceEntity == null ) {
            return null;
        }

        PriceDto.PriceDtoBuilder priceDto = PriceDto.builder();

        priceDto.productId( priceEntityProductId( priceEntity ) );
        priceDto.brandId( priceEntityBrandId( priceEntity ) );
        priceDto.startDate( priceEntity.getStartDate() );
        priceDto.endDate( priceEntity.getEndDate() );
        priceDto.priceList( priceEntity.getPriceList() );
        priceDto.price( priceEntity.getPrice() );
        priceDto.currency( priceEntity.getCurrency() );

        return priceDto.build();
    }

    private Long priceEntityProductId(PriceEntity priceEntity) {
        if ( priceEntity == null ) {
            return null;
        }
        ProductEntity product = priceEntity.getProduct();
        if ( product == null ) {
            return null;
        }
        Long id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long priceEntityBrandId(PriceEntity priceEntity) {
        if ( priceEntity == null ) {
            return null;
        }
        BrandEntity brand = priceEntity.getBrand();
        if ( brand == null ) {
            return null;
        }
        Long id = brand.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
