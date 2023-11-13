package com.compassuol.sp.challenge.msuser.controller;

import com.compassuol.sp.challenge.msuser.model.entity.EventNotification;
import com.compassuol.sp.challenge.msuser.model.dto.AuthenticationDto;
import com.compassuol.sp.challenge.msuser.model.dto.LoginResponseDTO;
import com.compassuol.sp.challenge.msuser.model.dto.UserRequestDto;
import com.compassuol.sp.challenge.msuser.model.dto.UserResponseDto;
import com.compassuol.sp.challenge.msuser.services.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.validation.Valid;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/users")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserRequestDto userRequestDto){
        try {
            EventNotification eventNotification = new EventNotification();
            eventNotification.setNotification("CREATED");
            eventNotification.setEmail(userRequestDto.getEmail());
            //eventNotification.setDate(LocalDateTime.now().toString());

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            String jsonMessage = objectMapper.writeValueAsString(eventNotification);

            rabbitTemplate.convertAndSend("user-event-exchange", "user-event", jsonMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDto authenticationDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.login(authenticationDto));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsersById(id));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, userRequestDto));
    }

    @PutMapping("/users/{id}/password")
    public ResponseEntity<UserResponseDto> updatePassword(@PathVariable Long id, @RequestBody String password){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updatePassword(id, password));
    }
}
