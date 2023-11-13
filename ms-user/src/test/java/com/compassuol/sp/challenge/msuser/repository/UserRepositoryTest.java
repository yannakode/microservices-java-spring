package com.compassuol.sp.challenge.msuser.repository;

import com.compassuol.sp.challenge.msuser.model.entity.User;
import com.compassuol.sp.challenge.msuser.services.impl.commons.UserConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByCpf_WhenCpfExists_ExpectUserWithMatchingCpf() {
        User user = new User();
        user.setCpf(UserConstants.USER_REQUEST_DTO.getCpf());
        userRepository.save(user);
        Optional<User> foundUser = userRepository.findByCpf(UserConstants.USER_REQUEST_DTO.getCpf());

        assertTrue(foundUser.isPresent());
        assertEquals(UserConstants.USER_REQUEST_DTO.getCpf(), foundUser.get().getCpf());
    }

    @Test
    public void findByEmail_WhenEmailExists_ExpectUserWithMatchingEmail() {
        User user = new User();
        user.setEmail(UserConstants.USER_REQUEST_DTO.getEmail());
        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByEmail(UserConstants.USER_REQUEST_DTO.getEmail());

        assertTrue(foundUser.isPresent());
        assertEquals(UserConstants.USER_REQUEST_DTO.getEmail(), foundUser.get().getEmail());
    }

    @Test
    public void saveUser_WithValidData_ShouldPersistUser() {
        User user = new User();
        user.setCpf(UserConstants.USER_REQUEST_DTO.getCpf());
        user.setEmail(UserConstants.USER_REQUEST_DTO.getEmail());
        user.setFirstName(UserConstants.USER_REQUEST_DTO.getFirstName());
        user.setLastName(UserConstants.USER_REQUEST_DTO.getLastName());
        user.setBirthdate(UserConstants.USER_REQUEST_DTO.getBirthdate());
        user.setPassword(UserConstants.USER_REQUEST_DTO.getPassword());
        user.setActive(UserConstants.USER_REQUEST_DTO.isActive());
        user.setRole(UserConstants.USER_REQUEST_DTO.getRole());

        userRepository.save(user);

        Optional<User> savedUser = userRepository.findById(user.getUserId());
        assertTrue(savedUser.isPresent());
        assertEquals(user, savedUser.get());
    }
}
