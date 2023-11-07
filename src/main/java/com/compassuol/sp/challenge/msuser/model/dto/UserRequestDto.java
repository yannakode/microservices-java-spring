package com.compassuol.sp.challenge.msuser.model.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @Size(min = 3, message = "First name must have at least 3 characters")
    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @Size(min = 3, message = "First name must have at least 3 characters")
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @CPF(message = "Invalid CPF")
    @NotBlank(message = "CPF cannot be blank")
    private String cpf;

    private Date birthdate;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    private boolean active;
}
