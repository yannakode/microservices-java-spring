package com.compassuol.sp.challenge.msuser.controller;

import com.compassuol.sp.challenge.msuser.model.entity.EventNotification;
import com.compassuol.sp.challenge.msuser.model.dto.AuthenticationDto;
import com.compassuol.sp.challenge.msuser.model.dto.LoginResponseDTO;
import com.compassuol.sp.challenge.msuser.model.dto.UserRequestDto;
import com.compassuol.sp.challenge.msuser.model.dto.UserResponseDto;
import com.compassuol.sp.challenge.msuser.model.entity.User;
import com.compassuol.sp.challenge.msuser.model.entity.enums.Event;
import com.compassuol.sp.challenge.msuser.repository.UserRepository;
import com.compassuol.sp.challenge.msuser.services.NotificationService;
import com.compassuol.sp.challenge.msuser.services.impl.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.validation.Valid;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserRequestDto userRequestDto) throws JsonProcessingException {
        notificationService.sendMessage(userRequestDto.getEmail(), Event.CREATE.getValue());
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDto authenticationDto) throws JsonProcessingException {
        notificationService.sendMessage(authenticationDto.email().toString(), Event.LOGIN.getValue());
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.login(authenticationDto));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsersById(id));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) throws JsonProcessingException {
        notificationService.sendMessage(userRequestDto.getEmail(), Event.UPDATE.getValue());
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, userRequestDto));
    }

    @PutMapping("/users/{id}/password")
    public ResponseEntity<UserResponseDto> updatePassword(@PathVariable Long id, @RequestBody String password) throws JsonProcessingException {
        String event = "UPDATE";
        Optional<User> user = userRepository.findById(id);
        notificationService.sendMessage(user.get().getEmail(), Event.UPDATE_PASSWORD.getValue());
        return ResponseEntity.status(HttpStatus.OK).body(userService.updatePassword(id, password));
    }
}
