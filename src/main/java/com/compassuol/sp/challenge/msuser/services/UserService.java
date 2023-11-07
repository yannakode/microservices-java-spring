package com.compassuol.sp.challenge.msuser.services;

import com.compassuol.sp.challenge.msuser.model.dto.UserRequestDto;
import com.compassuol.sp.challenge.msuser.model.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto userRequestDto);
}
