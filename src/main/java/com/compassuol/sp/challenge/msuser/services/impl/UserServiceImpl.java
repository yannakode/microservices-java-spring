package com.compassuol.sp.challenge.msuser.services.impl;

import com.compassuol.sp.challenge.msuser.exceptions.InvalidDataException;
import com.compassuol.sp.challenge.msuser.infra.security.TokenService;
import com.compassuol.sp.challenge.msuser.model.dto.AuthenticationDto;
import com.compassuol.sp.challenge.msuser.model.dto.LoginResponseDTO;
import com.compassuol.sp.challenge.msuser.model.dto.UserRequestDto;
import com.compassuol.sp.challenge.msuser.model.dto.UserResponseDto;
import com.compassuol.sp.challenge.msuser.model.entity.User;
import com.compassuol.sp.challenge.msuser.repository.UserRepository;
import com.compassuol.sp.challenge.msuser.services.UserService;
import com.compassuol.sp.challenge.msuser.services.assembler.UserDtoAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDtoAssembler assembler;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }

    public LoginResponseDTO login(AuthenticationDto data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return new LoginResponseDTO(token);
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        var user = assembler.toModel(userRequestDto);

        if(user.getFirstName().length() < 3) throw new InvalidDataException("First Name field must contain 3 or more characters.", "FirstName");
        if(user.getLastName().length() < 3) throw new InvalidDataException("Last Name field must contain 3 or more characters.", "FirstName");

        Optional<User> getCPFResponse = Optional.ofNullable(userRepository.findByCpf(user.getCpf()));
        if(!getCPFResponse.isEmpty()) throw new DataIntegrityViolationException("User CPF is already registered");

        Optional<User> getEmailResponse = Optional.ofNullable(userRepository.findByEmail(user.getEmail()));
        if(getEmailResponse.isPresent()) throw new DataIntegrityViolationException("User Email is already registered");

        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());
        user.setPassword(encodedPassword);

        var newUser = userRepository.save(user);
        return assembler.toDto(newUser);
    }
}
