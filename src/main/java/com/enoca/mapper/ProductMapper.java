package com.enoca.mapper;

import com.enoca.entity.Product;
import com.enoca.payload.request.ProductRequest;
import com.enoca.payload.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    public ProductResponse mapProductToProductResponse(Product product){
        return ProductResponse.builder()
                .name(product.getName())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .creationDate(product.getCreationDate())
                .build();
    }

    public Product mapProductRequestToProduct(ProductRequest productRequest){

        return Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .stockQuantity(productRequest.getStockQuantity())
                .creationDate(LocalDateTime.now())
                .build();
    }
}
