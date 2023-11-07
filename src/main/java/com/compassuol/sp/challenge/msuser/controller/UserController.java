package com.compassuol.sp.challenge.msuser.controller;

import com.compassuol.sp.challenge.msuser.model.dto.UserRequestDto;
import com.compassuol.sp.challenge.msuser.model.dto.UserResponseDto;
import com.compassuol.sp.challenge.msuser.services.UserService;
import com.compassuol.sp.challenge.msuser.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserRequestDto userRequestDto){
        System.out.println(userRequestDto.getFirstName());
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequestDto));
    }
}
