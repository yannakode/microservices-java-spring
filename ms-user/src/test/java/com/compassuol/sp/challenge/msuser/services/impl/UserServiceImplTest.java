package com.compassuol.sp.challenge.msuser.services.impl;

import com.compassuol.sp.challenge.msuser.exceptions.InvalidDataException;
import com.compassuol.sp.challenge.msuser.infra.security.TokenService;
import com.compassuol.sp.challenge.msuser.model.dto.AuthenticationDto;
import com.compassuol.sp.challenge.msuser.model.dto.LoginResponseDTO;
import com.compassuol.sp.challenge.msuser.model.dto.UserRequestDto;
import com.compassuol.sp.challenge.msuser.model.dto.UserResponseDto;
import com.compassuol.sp.challenge.msuser.model.entity.User;
import com.compassuol.sp.challenge.msuser.repository.UserRepository;
import com.compassuol.sp.challenge.msuser.services.assembler.UserDtoAssembler;
import com.compassuol.sp.challenge.msuser.services.impl.UserServiceImpl;
import com.compassuol.sp.challenge.msuser.services.impl.commons.UserConstants;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.compassuol.sp.challenge.msuser.services.impl.commons.UserConstants.USER_REQUEST_DTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDtoAssembler assembler;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TokenService tokenService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserDtoAssembler userDtoAssembler;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


}









