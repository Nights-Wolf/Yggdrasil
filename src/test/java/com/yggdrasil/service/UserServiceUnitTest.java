package com.yggdrasil.service;

import com.yggdrasil.databaseInterface.UserDatabase;
import com.yggdrasil.model.ChangeEmail;
import com.yggdrasil.model.ChangePassword;
import com.yggdrasil.model.Users;
import lombok.Getter;
import lombok.Setter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceUnitTest {

    @Mock
    private UserDatabase userDatabase;
    @Mock
    private UserService userService;
    @Mock
    private PasswordEncoder encoder;
    private String secret = "1111";
    private Users users = new Users(1L, "Dawid" , "Całkowski", "koko", "dawinavi@gmail.com", "Podolska",
            "81-200", "Sopot", "Pomorskie", 1111L, true, true, true, true,
            true, true, true, "USER");
    @Mock
    private HttpServletRequest httpRequest;
    @Mock
    private HttpServletResponse httpResponse;

    @BeforeEach
    void setUp() {
        userService = new UserService(userDatabase, encoder, secret);
    }

    @AfterEach
    void tearDown() {
        userDatabase.deleteAll();
    }

    @Test
    void shouldCreateUser() {

        Users users = new Users(1L, "Dawid" , "Całkowski", "koko", "dawinavi@gmail.com", "Podolska",
                "81-200", "Sopot", "Pomorskie", 1111L, true, true, true, true,
                true, true, true, "USER");

        userService.createUser(users);

        ArgumentCaptor<Users> usersArgumentCaptor = ArgumentCaptor.forClass(Users.class);

        Mockito.verify(userDatabase).save(usersArgumentCaptor.capture());

        Users userCaptured = usersArgumentCaptor.getValue();

        Assertions.assertThat(userCaptured).isEqualTo(users);
    }

    @Test
    @Disabled
    void getUser() {
        Mockito.when(userDatabase.findByEmail(users.getEmail())).thenReturn(users);
        Mockito.when(httpRequest.getHeader("AUTHORIZATION")).thenReturn("AUTHORIZATION");

        ResponseEntity<Users> response = userService.getUser(httpRequest, httpResponse);

        Users responseUser = response.getBody();

        org.junit.jupiter.api.Assertions.assertEquals(users, responseUser);
    }

    @Test
    void editUser() {
        Users userEdited = new Users(1L, "Kamila" , "Kamala", "koko", "dawinavi@gmail.com", "Podolska",
                "81-200", "Sopot", "Pomorskie", 1111L, true, true, true, true,
                true, true, true, "USER");
        Mockito.when(userDatabase.findByEmail(users.getEmail())).thenReturn(users);

        userService.editUser(userEdited);

        ArgumentCaptor<Users> usersArgumentCaptor = ArgumentCaptor.forClass(Users.class);
        Mockito.verify(userDatabase).save(usersArgumentCaptor.capture());
        Users capturedUser = usersArgumentCaptor.getValue();

        org.junit.jupiter.api.Assertions.assertNotEquals(capturedUser.getUsername(), "Dawid");
    }

    @Test
    void setToken() {
        Mockito.when(userDatabase.findById(users.getId())).thenReturn(Optional.of(users));

        Long refreshToken = Long.valueOf(2);

        userService.setToken(users.getId(), refreshToken);

        ArgumentCaptor<Users> usersArgumentCaptor = ArgumentCaptor.forClass(Users.class);
        Mockito.verify(userDatabase).save(usersArgumentCaptor.capture());

        Users capturedUser = usersArgumentCaptor.getValue();

        org.junit.jupiter.api.Assertions.assertEquals(capturedUser.getRefreshToken(), refreshToken);
    }

    @Test
    void deleteToken() {
        Mockito.when(userDatabase.findById(users.getId())).thenReturn(Optional.ofNullable(users));

        userService.deleteToken(users.getId());

        ArgumentCaptor<Users> usersArgumentCaptor = ArgumentCaptor.forClass(Users.class);
        Mockito.verify(userDatabase).save(usersArgumentCaptor.capture());
        Users capturedUser = usersArgumentCaptor.getValue();

        org.junit.jupiter.api.Assertions.assertEquals(capturedUser.getRefreshToken(), null);
    }

    @Test
    @Disabled
    void grantAdmin() {
    }

    @Test
    void findByEmail() {
        Mockito.when(userDatabase.findByEmail(users.getEmail())).thenReturn(users);

        Users response = userService.findByEmail(users.getEmail());

        org.junit.jupiter.api.Assertions.assertEquals(response, users);
    }

    @Test
    void remindPassword() {
        Mockito.when(userDatabase.findByEmail(users.getEmail())).thenReturn(users);

        userService.remindPassword(users.getEmail(), users);

        ArgumentCaptor<Users> usersArgumentCaptor = ArgumentCaptor.forClass(Users.class);
        Mockito.verify(userDatabase).save(usersArgumentCaptor.capture());
        Users capturedUser = usersArgumentCaptor.getValue();

        org.junit.jupiter.api.Assertions.assertEquals(capturedUser.getPassword(), users.getPassword());
    }

    @Test
    void changePassword() {
        ChangePassword changePassword = new ChangePassword(users.getEmail(), users.getPassword(), "roko");
        Mockito.when(userDatabase.save(users)).thenReturn(users);

        userService.createUser(users);

        Mockito.when(userDatabase.findByEmail(users.getEmail())).thenReturn(users);

        ResponseEntity response = userService.changePassword(changePassword);
        HttpStatus httpStatus = HttpStatus.ACCEPTED;
        HttpStatus responseStatus = response.getStatusCode();

        org.junit.jupiter.api.Assertions.assertNotEquals(httpStatus, responseStatus);
    }

    @Test
    void changeEmail() {
        ChangeEmail changeEmail = new ChangeEmail(users.getEmail(), "dawinavi@wp.pl");
        Mockito.when(userDatabase.findByEmail(users.getEmail())).thenReturn(users);

        userService.changeEmail(changeEmail);
        ArgumentCaptor<Users> usersArgumentCaptor = ArgumentCaptor.forClass(Users.class);
        Mockito.verify(userDatabase).save(usersArgumentCaptor.capture());
        Users capturedUser = usersArgumentCaptor.getValue();

        org.junit.jupiter.api.Assertions.assertNotEquals(changeEmail.getNewEmail(), "dawinavi@gmail.com");
    }

    @Test
    void rememberMeCheck() {
        Mockito.when(userDatabase.findByEmail(users.getEmail())).thenReturn(users);

        userService.rememberMeCheck(users.getEmail());

        ArgumentCaptor<Users> usersArgumentCaptor = ArgumentCaptor.forClass(Users.class);
        Mockito.verify(userDatabase).save(usersArgumentCaptor.capture());
        Users capturedUser = usersArgumentCaptor.getValue();

        org.junit.jupiter.api.Assertions.assertEquals(true, capturedUser.isRememberMe());
    }
}