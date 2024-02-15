package com.enoca.payload.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemRequest {

    @NotNull(message = "Product quantity can not be null")
    @Min(value = 1, message = "Quantity can not be smaller than 1")
    private Integer quantity;

    @NotNull(message = "Product ID can not be null")
    private Long productId;

}
