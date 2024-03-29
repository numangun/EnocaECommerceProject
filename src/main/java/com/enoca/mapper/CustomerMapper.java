package com.enoca.mapper;

import com.enoca.entity.Customer;
import com.enoca.payload.request.CustomerRequest;
import com.enoca.payload.response.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CustomerMapper {

    public CustomerResponse mapCustomerToCustomerResponse(Customer customer){
        return CustomerResponse.builder()
                .id(customer.getId())
                .username(customer.getUsername())
                .name(customer.getName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .creationDate(customer.getCreationDate())
                .build();

    }

    public Customer mapCustomerRequestToCustomer(CustomerRequest customerRequest){

        return Customer.builder()
                .username(customerRequest.getUsername())
                .name(customerRequest.getName())
                .lastName(customerRequest.getLastName())
                .email(customerRequest.getEmail())
                .creationDate(LocalDateTime.now())
                .build();
    }

}
