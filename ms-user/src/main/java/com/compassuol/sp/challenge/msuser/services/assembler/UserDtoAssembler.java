package com.compassuol.sp.challenge.msuser.services.assembler;

import com.compassuol.sp.challenge.msuser.model.dto.UserRequestDto;
import com.compassuol.sp.challenge.msuser.model.dto.UserResponseDto;
import com.compassuol.sp.challenge.msuser.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDtoAssembler {

    public UserResponseDto toDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        userResponseDto.setCpf(user.getCpf());
        userResponseDto.setBirthdate(user.getBirthdate());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setActive(user.isActive());
        userResponseDto.setRole(user.getRole());
        return userResponseDto;
    }

    public User toModel(UserRequestDto userRequestDto) {
        User user = new User();
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setCpf(userRequestDto.getCpf());
        user.setBirthdate(userRequestDto.getBirthdate());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        user.setActive(userRequestDto.isActive());
        user.setRole(userRequestDto.getRole());
        return user;
    }
}