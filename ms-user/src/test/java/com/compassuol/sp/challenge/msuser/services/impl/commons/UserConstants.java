package com.compassuol.sp.challenge.msuser.services.impl.commons;

import com.compassuol.sp.challenge.msuser.model.dto.UserRequestDto;
import com.compassuol.sp.challenge.msuser.model.dto.UserResponseDto;
import com.compassuol.sp.challenge.msuser.model.entity.enums.UserRole;

import java.util.Date;

public class UserConstants {

    public static final UserRequestDto USER_REQUEST_DTO = new UserRequestDto("User name", "Last name", "123.456.789-09", new Date(), "email@example.com", "password", true, UserRole.USER);
    public static final UserRequestDto USER_REQUEST_DTO_INVALID_NAME = new UserRequestDto("", "Last name", "123.456.789-09", new Date(), "email@example.com", "password", true, UserRole.USER);
    public static final UserRequestDto USER_REQUEST_DTO_INVALID_CPF = new UserRequestDto("User name", "Last name", "invalid_cpf", new Date(), "email@example.com", "password", true, UserRole.USER);

    public static final UserResponseDto USER_RESPONSE_DTO;

    static {
        USER_RESPONSE_DTO = new UserResponseDto();
        USER_RESPONSE_DTO.setFirstName(USER_REQUEST_DTO.getFirstName());
        USER_RESPONSE_DTO.setLastName(USER_REQUEST_DTO.getLastName());
        USER_RESPONSE_DTO.setCpf(USER_REQUEST_DTO.getCpf());
        USER_RESPONSE_DTO.setBirthdate(USER_REQUEST_DTO.getBirthdate());
        USER_RESPONSE_DTO.setEmail(USER_REQUEST_DTO.getEmail());
        USER_RESPONSE_DTO.setActive(USER_REQUEST_DTO.isActive());
        USER_RESPONSE_DTO.setRole(USER_REQUEST_DTO.getRole());
    }
}
