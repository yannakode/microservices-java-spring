package com.compassuol.sp.challenge.msuser.services.impl;

import com.compassuol.sp.challenge.msuser.exceptions.InvalidDataException;
import com.compassuol.sp.challenge.msuser.infra.security.TokenService;
import com.compassuol.sp.challenge.msuser.model.dto.AuthenticationDto;
import com.compassuol.sp.challenge.msuser.model.dto.LoginResponseDTO;
import com.compassuol.sp.challenge.msuser.model.dto.UserRequestDto;
import com.compassuol.sp.challenge.msuser.model.dto.UserResponseDto;
import com.compassuol.sp.challenge.msuser.model.entity.EventNotification;
import com.compassuol.sp.challenge.msuser.model.entity.User;
import com.compassuol.sp.challenge.msuser.repository.UserRepository;
import com.compassuol.sp.challenge.msuser.services.NotificationService;
import com.compassuol.sp.challenge.msuser.services.UserService;
import com.compassuol.sp.challenge.msuser.services.assembler.UserDtoAssembler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDtoAssembler assembler;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final NotificationService notificationService;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Optional<User> loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }

    public LoginResponseDTO login(AuthenticationDto data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return new LoginResponseDTO(token);
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto){
        var user = assembler.toModel(userRequestDto);

        if(user.getFirstName().length() < 3) throw new InvalidDataException("First Name field must contain 3 or more characters.", "FirstName");
        if(user.getLastName().length() < 3) throw new InvalidDataException("Last Name field must contain 3 or more characters.", "FirstName");

        Optional<User> getCPFResponse = userRepository.findByCpf(user.getCpf());
        if(getCPFResponse.isPresent()) throw new DataIntegrityViolationException("User CPF is already registered");

        Optional<User> getEmailResponse = userRepository.findByEmail(userRequestDto.getEmail());
        if(getEmailResponse.isPresent()) throw new DataIntegrityViolationException("User Email is already registered");

        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());
        user.setPassword(encodedPassword);

        var newUser = userRepository.save(user);
        return assembler.toDto(newUser);
    }

    @Override
    public UserResponseDto getUsersById(Long id){
        var findUserIfExists = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return assembler.toDto(findUserIfExists);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto){
        var findUserIfExists = userRepository.findById(id);

        if(userRequestDto.getFirstName().length() < 3) throw new InvalidDataException("First Name field must contain 3 or more characters.", "FirstName");
        if(userRequestDto.getLastName().length() < 3) throw new InvalidDataException("Last Name field must contain 3 or more characters.", "LastName");

        if (!findUserIfExists.get().getCpf().equals(userRequestDto.getCpf())) {
            Optional<User> getCPFResponse = userRepository.findByCpf(userRequestDto.getCpf());
            if (getCPFResponse.isPresent()) {
                throw new DataIntegrityViolationException("User CPF is already registered");
            }
        }

        Optional<User> existingEmailUser = userRepository.findByEmail(userRequestDto.getEmail());
        if(existingEmailUser.isPresent() && !existingEmailUser.get().getUserId().equals(id))
            throw new DataIntegrityViolationException("User Email is already registered");

        User user = findUserIfExists.get();
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setBirthdate(userRequestDto.getBirthdate());
        user.setEmail(userRequestDto.getEmail());

        userRepository.save(user);

        return assembler.toDto(user);
    }

    @Override
    public UserResponseDto updatePassword(Long id, String password){
        var findUserIfExists = userRepository.findById(id);
        if(findUserIfExists.isEmpty()) throw new UsernameNotFoundException("User not found");
        User user = findUserIfExists.get();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return assembler.toDto(user);
    }

}
