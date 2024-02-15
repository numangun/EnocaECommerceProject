package com.enoca.helper;

import com.enoca.entity.Customer;
import com.enoca.exception.ResourceNotFoundException;
import com.enoca.message.ErrorMessages;
import com.enoca.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerHelper {

    private final CustomerRepository customerRepository;

    public Customer existsById(Long id){
        return customerRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_CUSTOMER_ID,id))
        );
    }

}
