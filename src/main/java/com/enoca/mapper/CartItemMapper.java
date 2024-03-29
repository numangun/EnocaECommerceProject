package com.enoca.mapper;

import com.enoca.entity.Cart;
import com.enoca.entity.CartItem;
import com.enoca.entity.Product;
import com.enoca.payload.request.CartItemRequest;
import com.enoca.payload.response.CartItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CartItemMapper {

    public CartItem mapCartItemRequestToCartItem(CartItemRequest cartItemRequest, Product product, Cart cart){

        return CartItem.builder()
                .quantity(cartItemRequest.getQuantity())
                .cart(cart)
                .product(product)
                .creationDate(LocalDateTime.now())
                .build();
    }

    public CartItemResponse mapCartItemToCartItemResponse(CartItem cartItem){

        return CartItemResponse.builder()
                .id(cartItem.getId())
                .quantity(cartItem.getQuantity())
                .productName(cartItem.getProduct().getName())
                .productPrice(cartItem.getProductPrice())
                .creationDate(cartItem.getCreationDate())
                .build();

    }

}
