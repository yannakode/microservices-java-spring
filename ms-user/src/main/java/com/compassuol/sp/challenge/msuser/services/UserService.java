package com.compassuol.sp.challenge.msuser.services;

import com.compassuol.sp.challenge.msuser.model.dto.UserRequestDto;
import com.compassuol.sp.challenge.msuser.model.dto.UserResponseDto;
import com.compassuol.sp.challenge.msuser.model.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface UserService {
    Optional<User> loadUserByUsername(String email) throws UsernameNotFoundException;

    UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto getUsersById(Long id);

    UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);

    UserResponseDto updatePassword(Long id, String password);
}
