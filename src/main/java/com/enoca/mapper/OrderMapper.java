package com.enoca.mapper;

import com.enoca.entity.CartItem;
import com.enoca.entity.Customer;
import com.enoca.entity.Order;
import com.enoca.helper.CartHelper;
import com.enoca.payload.request.OrderRequest;
import com.enoca.payload.response.OrderResponse;
import com.enoca.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final CartService cartService;
    private final CartItemMapper cartItemMapper;
    private final CartHelper cartHelper;

    public Order mapOrderRequestToOrder(OrderRequest orderRequest,
                                        Customer customer,
                                        List<CartItem> cartItems,
                                        String code){
        return Order.builder()
                .code(code)
                .cartItem(cartItems)
                .customer(customer)
                .totalPrice(cartHelper.cartTotalPriceCalculator(cartItems))
                .creationDate(LocalDateTime.now())
                .build();
    }

    public OrderResponse mapOrderToOrderResponse(Order order){
        return OrderResponse.builder()
                .id(order.getId())
                .totalPrice(order.getTotalPrice())
                .code(order.getCode())
                .customerName(order.getCustomer().getName())
                .cartItems(order.getCartItem()
                        .stream()
                        .map(cartItemMapper::mapCartItemToCartItemResponse)
                        .collect(Collectors.toList()))
                .creationDate(order.getCreationDate())
                .build();
    }

}
