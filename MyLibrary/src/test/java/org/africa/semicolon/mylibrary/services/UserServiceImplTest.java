package org.africa.semicolon.mylibrary.services;

import org.africa.semicolon.mylibrary.data.models.Role;
import org.africa.semicolon.mylibrary.data.models.User;
import org.africa.semicolon.mylibrary.data.repositories.UserRepo;
import org.africa.semicolon.mylibrary.dtos.requests.LoginUserRequest;
import org.africa.semicolon.mylibrary.dtos.requests.RegisterUserRequest;
import org.africa.semicolon.mylibrary.dtos.responses.LoginUserResponse;
import org.africa.semicolon.mylibrary.dtos.responses.RegisterUserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceImplTest {
    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setFullName("usman eletu");
        request.setEmail("usman@gmail.com");
        request.setPhoneNumber("");
        request.setPassword("password");

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(request.getPassword());

        when(userRepo.save(user)).thenReturn(user);
        RegisterUserResponse response = userService.register(request);
        assertNotNull(response);
        assertEquals("usman eletu", response.getFullName());
        assertEquals("Registered successfully Welcome usman eletu",response.getMessage());

        verify(userRepo,times(1)).save(user);
    }
    @Test
    public void testUserCanLogin() {
        LoginUserRequest request = new LoginUserRequest();
        request.setEmail("allieric12@gmail.com");
        request.setPassword("password");

        User user = new User();
        user.setFullName("usman eletu");
        user.setEmail("allieric12@gmail.com");
        user.setPhoneNumber("07082573315");
        user.setPassword("password");
        user.setRole(Role.USER);

        when(userRepo.findByEmail("allieric12@gmail.com")).thenReturn(Optional.of(user));

        LoginUserResponse response = userService.login(request);

        assertNotNull(response);
        assertEquals("Login successful. welcome usman eletu",response.getMessage());
        assertEquals(Role.USER.name(),response.getRole());

        verify(userRepo,times(1)).findByEmail("allieric12@gmail.com");
    }
    @Test
    public void testLibrarianCanLogin() {
        LoginUserRequest request = new LoginUserRequest();
        request.setEmail("allieric12@gmail.com");
        request.setPassword("password");

        User user = new User();
        user.setFullName("usman eletu");
        user.setEmail("allieric12@gmail.com");
        user.setPhoneNumber("07082573315");
        user.setPassword("password");
        user.setRole(Role.LIBRARIAN);

        when(userRepo.findByEmail("allieric12@gmail.com")).thenReturn(Optional.of(user));

        LoginUserResponse response = userService.login(request);

        assertNotNull(response);
        assertEquals("Login successful. welcome usman eletu",response.getMessage());
        assertEquals(Role.LIBRARIAN.name(),response.getRole());

        verify(userRepo,times(1)).findByEmail("allieric12@gmail.com");

    }
    @Test
    public void TestAdminCanLogin() {
        LoginUserRequest request = new LoginUserRequest();
        request.setEmail("allieric12@gmail.com");
        request.setPassword("password");

        User user = new User();
        user.setFullName("usman eletu");
        user.setEmail("allieric12@gmail.com");
        user.setPhoneNumber("07082573315");
        user.setPassword("password");
        user.setRole(Role.ADMIN);

        when(userRepo.findByEmail("allieric12@gmail.com")).thenReturn(Optional.of(user));

        LoginUserResponse response = userService.login(request);

        assertNotNull(response);
        assertEquals("Login successful. welcome usman eletu",response.getMessage());
        assertEquals(Role.ADMIN.name(),response.getRole());

        verify(userRepo,times(1)).findByEmail("allieric12@gmail.com");
    }
  
}