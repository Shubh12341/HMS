package com.hospitalmanagement.payload;

import lombok.Data;
import javax.validation.constraints.Email;

@Data
public class LoginDto {

    @Email(message = "Enter valid email or usernamee")
    private String usernameOrEmail;

    private String password;
}