package com.yggdrasil.service;

import com.yggdrasil.databaseInterface.UserDatabase;
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
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
@Setter
@Getter
class UserServiceUnitTest {

    @Mock
    private UserDatabase userDatabase;
    @Mock
    private UserService userService;
    @Mock
    private PasswordEncoder encoder;
    private String secret = "1111";

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
    }

    @Test
    @Disabled
    void editUser() {
        Users users = new Users(1L, "Dawid" , "Całkowski", "koko", "dawinavi@gmail.com", "Podolska",
                "81-200", "Sopot", "Pomorskie", 1111L, true, true, true, true,
                true, true, true, "USER");
        userDatabase.save(users);
        System.out.println(userDatabase.findByEmail(users.getEmail()));
        Users userEdited = new Users(1L, "Kamila" , "Kamala", "koko", "dawinavi@gmail.com", "Podolska",
                "81-200", "Sopot", "Pomorskie", 1111L, true, true, true, true,
                true, true, true, "USER");

        userService.editUser(userEdited);

        Assertions.assertThat(users.getUsername()).isNotEqualTo(userEdited.getUsername());
    }

    @Test
    @Disabled
    void setToken() {
    }

    @Test
    @Disabled
    void deleteToken() {
    }

    @Test
    @Disabled
    void grantAdmin() {
    }

    @Test
    @Disabled
    void findByEmail() {
    }

    @Test
    @Disabled
    void remindPassword() {
    }

    @Test
    @Disabled
    void changePassword() {
    }

    @Test
    @Disabled
    void changeEmail() {
    }

    @Test
    @Disabled
    void rememberMeCheck() {
    }
}