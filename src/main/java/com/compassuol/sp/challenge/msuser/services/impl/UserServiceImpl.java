package com.compassuol.sp.challenge.msuser.services.impl;

import com.compassuol.sp.challenge.msuser.model.dto.UserRequestDto;
import com.compassuol.sp.challenge.msuser.model.dto.UserResponseDto;
import com.compassuol.sp.challenge.msuser.repository.UserRepository;
import com.compassuol.sp.challenge.msuser.services.UserService;
import com.compassuol.sp.challenge.msuser.services.assembler.UserDtoAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDtoAssembler assembler;
    private final UserRepository userRepository;

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        var user = assembler.toModel(userRequestDto);
        var newUser = userRepository.save(user);
        return assembler.toDto(newUser);
    }
}
