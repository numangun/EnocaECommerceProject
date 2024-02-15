package com.enoca.helper;

import com.enoca.entity.Cart;
import com.enoca.entity.CartItem;
import com.enoca.exception.ResourceNotFoundException;
import com.enoca.message.ErrorMessages;
import com.enoca.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CartHelper {

    private final CartRepository cartRepository;

    public Double cartTotalPriceCalculator(List<CartItem> cartItems){

        double totalPrice = 0.0;

        for (CartItem cartItem:cartItems){
            totalPrice += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        return totalPrice;
    }
    public Cart existsById(Long id){
        return cartRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_CART_ID, id))
        );
    }

}
