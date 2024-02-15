package com.enoca.payload.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartRequest {

    @NotNull(message = "Please enter customer ID")
    private Long customerId;

}
