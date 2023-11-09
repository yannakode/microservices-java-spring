package com.compassuol.sp.challenge.msuser.services;

import com.compassuol.sp.challenge.msuser.model.dto.UserRequestDto;
import com.compassuol.sp.challenge.msuser.model.dto.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    UserResponseDto createUser(UserRequestDto userRequestDto);
}
