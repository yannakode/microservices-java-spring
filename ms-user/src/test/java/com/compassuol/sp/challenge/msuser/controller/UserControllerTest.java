package com.compassuol.sp.challenge.msuser.controller;

import com.compassuol.sp.challenge.msuser.model.dto.AuthenticationDto;
import com.compassuol.sp.challenge.msuser.model.dto.LoginResponseDTO;
import com.compassuol.sp.challenge.msuser.model.dto.UserRequestDto;
import com.compassuol.sp.challenge.msuser.model.dto.UserResponseDto;
import com.compassuol.sp.challenge.msuser.services.impl.UserServiceImpl;
import com.compassuol.sp.challenge.msuser.services.impl.commons.UserConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser_WithValidUserData_ReturnsCreatedStatus() throws JsonProcessingException {
        when(userService.createUser(eq(UserConstants.USER_REQUEST_DTO))).thenReturn(UserConstants.USER_RESPONSE_DTO);

        ResponseEntity<UserResponseDto> responseEntity = userController.createUser(UserConstants.USER_REQUEST_DTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode(), "201 CREATED");

        verify(userService, times(1)).createUser(eq(UserConstants.USER_REQUEST_DTO));
    }

    @Test
    void getUser_WithValidUserId_ReturnsUserResponse() {
        Long userId = 1L;

        UserResponseDto userResponseDto = new UserResponseDto();

        when(userService.getUsersById(userId)).thenReturn(userResponseDto);

        ResponseEntity<UserResponseDto> responseEntity = userController.getUser(userId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userResponseDto, responseEntity.getBody());

        verify(userService, times(1)).getUsersById(userId);
    }

    @Test
    void updateUser_WithValidUserIdAndUserData_ReturnsOkStatus() throws JsonProcessingException {
        Long userId = 1L;

        UserRequestDto userRequestDto = new UserRequestDto();

        UserResponseDto userResponseDto = new UserResponseDto();

        when(userService.updateUser(eq(userId), any(UserRequestDto.class))).thenReturn(userResponseDto);

        ResponseEntity<UserResponseDto> responseEntity = userController.updateUser(userId, userRequestDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userResponseDto, responseEntity.getBody());

        verify(userService, times(1)).updateUser(eq(userId), any(UserRequestDto.class));
    }
}

