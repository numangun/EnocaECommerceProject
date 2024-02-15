package com.enoca.payload.response;

import com.enoca.payload.response.abstractresponse.BaseAbstractResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL) // Cart ı boş olan müşterileri NullPointerException almadan döndürmek için kullanıldı
public class CartResponse extends BaseAbstractResponse {

    private String customerName;
    private Double totalPrice;
    private List<CartItemResponse>cartItems;


}
