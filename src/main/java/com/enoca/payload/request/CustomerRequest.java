package com.enoca.payload.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequest {

    @NotNull(message = "Please enter your username")
    @Size(min = 3, max = 16,message = "Your username should be between {min} and {max} chars")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your username must consist of the characters.")
    private String username;

    @NotNull(message = "Please enter your name")
    @Size(min = 3, max = 25,message = "Your name should be between {min} and {max} chars")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your name must consist of the characters.")
    private String name;

    @NotNull(message = "Please enter your last name")
    @Size(min = 2, max = 30,message = "Your last name should be between {min} and {max} chars")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your last name must consist of the characters.")
    private String lastName;

    @NotNull(message = "Please enter your email")
    @Email(message = "Please enter valid email")
    @Size(min=5, max=50 , message = "Your email should be between {min} and {max} chars")
    private String email;

}
