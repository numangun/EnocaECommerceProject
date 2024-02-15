package com.enoca.controller;

import com.enoca.payload.request.CartItemRequest;
import com.enoca.payload.response.CartItemResponse;
import com.enoca.payload.response.CartResponse;
import com.enoca.payload.response.ResponseMessage;
import com.enoca.service.CartItemService;
import com.enoca.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartItemService cartItemService;

    // Get Cart
    @GetMapping("getByCustomerId/{customerId}")
    public CartResponse getByCustomerId(@PathVariable Long customerId){
        return cartService.getByCustomerId(customerId);
    }

    // Empty Cart
    @PutMapping("/empty/{id}")
    public ResponseEntity<String> emptyCart(@PathVariable Long id){
        return ResponseEntity.ok(cartItemService.emptyCart(id));
    }

    // Add Product(Cart Item) to Cart
    @PatchMapping("/addProductToCart/{cartId}")
    public ResponseMessage<CartItemResponse> addCartItemToCart(@PathVariable Long cartId,
                                                               @RequestBody @Valid CartItemRequest cartItemRequest){
        return cartService.addCartItemToCart(cartId, cartItemRequest);
    }

    // Remove Product From Cart
    @DeleteMapping("/removeProductFromCart/{cartId}") // http://localhost:8080/cart/removeProductFromCart/2
    public ResponseEntity<String> removeCartItemFromCart(@PathVariable Long cartId,
                                                         @RequestParam Long cartItemId){
        return ResponseEntity.ok(cartService.removeCartItemFromCart(cartId, cartItemId));
    }



}
