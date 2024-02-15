package com.enoca.service;

import com.enoca.entity.Cart;
import com.enoca.entity.CartItem;
import com.enoca.entity.Customer;
import com.enoca.entity.Product;
import com.enoca.helper.CartHelper;
import com.enoca.helper.CustomerHelper;
import com.enoca.helper.ProductHelper;
import com.enoca.mapper.CartItemMapper;
import com.enoca.mapper.CartMapper;
import com.enoca.message.SuccessMessages;
import com.enoca.payload.request.CartItemRequest;
import com.enoca.payload.response.CartItemResponse;
import com.enoca.payload.response.CartResponse;
import com.enoca.payload.response.ResponseMessage;
import com.enoca.repository.CartItemRepository;
import com.enoca.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final CartItemMapper cartItemMapper;
    private final CartItemRepository cartItemRepository;
    private final CartHelper cartHelper;
    private final CustomerHelper customerHelper;
    private final ProductHelper productHelper;


    public CartResponse getByCustomerId(Long customerId) {
        Customer customer = customerHelper.existsById(customerId);

        return cartMapper.mapCartToCartResponse(customer.getCart());
    }


    public ResponseMessage<CartItemResponse> addCartItemToCart(Long cartId, CartItemRequest cartItemRequest) {
        Cart cart = cartHelper.existsById(cartId);
        Product product = productHelper.existById(cartItemRequest.getProductId());

        CartItem cartItem = cartItemMapper.mapCartItemRequestToCartItem(cartItemRequest, product, cart);

        cart.getCartItem().add(cartItem);
        cart.setTotalPriceInCart(
                cartHelper.cartTotalPriceCalculator(cart.getCartItem()));

        cartRepository.save(cart);
        cartItemRepository.save(cartItem);

        return ResponseMessage.<CartItemResponse> builder()
                .message(SuccessMessages.CART_ITEM_ADDED)
                .returnBody(cartItemMapper.mapCartItemToCartItemResponse(cartItem))
                .httpStatus(HttpStatus.OK)
                .build();
    }


    public String removeCartItemFromCart(Long cartId, Long cartItemId) {
        Cart cart = cartHelper.existsById(cartId);
        List<CartItem>cartItems = cart.getCartItem();

        for (CartItem cartItem:cartItems){
            if (cartItem.getId().equals(cartItemId)){
                cartItems.remove(cartItem);
                cartItemRepository.deleteById(cartItemId);
                break;
            }
        }

        cart.setCartItem(cartItems);
        cart.setTotalPriceInCart(cartHelper.cartTotalPriceCalculator(cartItems));
        cartRepository.save(cart);

        return SuccessMessages.CART_ITEM_REMOVED;
    }

}
