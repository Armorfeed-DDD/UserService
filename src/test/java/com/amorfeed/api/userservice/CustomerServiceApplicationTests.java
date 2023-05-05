package com.amorfeed.api.userservice;

import com.amorfeed.api.userservice.entity.User;
import com.amorfeed.api.userservice.repository.UserRepository;
import com.amorfeed.api.userservice.resource.ChangeEmailResource;
import com.amorfeed.api.userservice.resource.ChangePasswordResource;
import com.amorfeed.api.userservice.service.UserService;
import com.amorfeed.api.userservice.service.impl.UserImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class CustomerServiceApplicationTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserImpl userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void changeEmail() {
        User user=new User(1L,"Edery","edery@hotmail.com","12345");
        userRepository.save(user);
        String newEmail="edery77@hotmail.com";
        ChangeEmailResource changeEmailResource=new ChangeEmailResource(user.getEmail(),newEmail);
        userService.changeEmail(changeEmailResource);
        Optional<User> userExpected= userRepository.findByEmail(newEmail);
        assertThat(userExpected.get()).isNotNull();
        assertEquals(userExpected.get().getEmail(),newEmail);
    }

    @Test
    public void changePassword(){
        User user=new User(1L,"Edery","edery@hotmail.com","12345");
        userRepository.save(user);
        String newPassword="54321";
        ChangePasswordResource changePasswordResource=new ChangePasswordResource(user.getEmail(),user.getPassword(),newPassword);
        userService.changePassword(changePasswordResource);
        Optional<User> userExpected=userRepository.findByEmail(user.getEmail());
        assertThat(userExpected.get()).isNotNull();
        assertThat(passwordEncoder.matches(newPassword,userExpected.get().getPassword())).isTrue();
    }
}
