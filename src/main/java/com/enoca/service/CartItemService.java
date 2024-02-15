package com.enoca.service;

import com.enoca.entity.Cart;
import com.enoca.entity.CartItem;
import com.enoca.entity.Order;
import com.enoca.entity.Product;
import com.enoca.helper.CartHelper;
import com.enoca.helper.ProductHelper;
import com.enoca.mapper.CartItemMapper;
import com.enoca.message.SuccessMessages;
import com.enoca.payload.request.CartItemRequest;
import com.enoca.payload.response.CartItemResponse;
import com.enoca.payload.response.ResponseMessage;
import com.enoca.repository.CartItemRepository;
import com.enoca.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;
    private final CartRepository cartRepository;
    private final CartHelper cartHelper;
    private final ProductHelper productHelper;


    public ResponseMessage<CartItemResponse> saveCartItem(CartItemRequest cartItemRequest, Long cartId) {

        Product product = productHelper.existById(cartItemRequest.getProductId());
        Cart cart = cartHelper.existsById(cartId);

        CartItem cartItem = cartItemMapper.
                mapCartItemRequestToCartItem(cartItemRequest, product, cart);

        CartItem cartItemToSave = cartItemRepository.save(cartItem);

        cart.getCartItem().add(cartItemToSave);
        cartRepository.save(cart);

        return ResponseMessage.<CartItemResponse> builder()
                .message(SuccessMessages.CART_ITEM_ADDED)
                .returnBody(cartItemMapper.mapCartItemToCartItemResponse(cartItemToSave))
                .build();
    }

    public void deleteCartItemByCartId(Long cartId){
        List<CartItem> cartItems = cartItemRepository.getByCartIdAndOrderNull(cartId);

        for (CartItem cartItem:cartItems){
            cartItemRepository.deleteById(cartItem.getId());
        }
    }

    public String emptyCart(Long id) {

        Cart cart = cartHelper.existsById(id);
        deleteCartItemByCartId(id);

        cart.setCartItem(new ArrayList<>());
        cart.setTotalPriceInCart(0.0);

        cartRepository.save(cart);

        return SuccessMessages.CART_EMPTY;

    }

    public List<CartItem>getByCartId(Long cartId){
        return cartItemRepository.getByCartIdAndOrderNull(cartId);
    }

    public void updateCartItemOrder(Order order, List<CartItem>cartItems){

        for (CartItem cartItem:cartItems){
            cartItem.setOrder(order);
            cartItemRepository.save(cartItem);
        }
    }
    public void makeEmptyToCart(List<CartItem> cartItems) {

        Cart cart = cartItems.get(0).getCart();
        cart.setTotalPriceInCart(0.0);
        cartRepository.save(cart);

        for (CartItem cartItem:cartItems){
            cartItem.setCart(null);
            cartItemRepository.save(cartItem);
        }
    }

}
