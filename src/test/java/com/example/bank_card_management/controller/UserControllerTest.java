package com.example.bank_card_management.controller;

import com.example.bank_card_management.model.User;
import com.example.bank_card_management.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User();
        user1.setEmail("one@example.com");
        User user2 = new User();
        user2.setEmail("two@example.com");

        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userController.getAllUsers();

        assertEquals(2, users.size());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testGetASpecificUser() {
        User user = new User();
        user.setEmail("test@example.com");

        when(userService.getASpecificUser(1L)).thenReturn(user);

        User result = userController.getASpecificUser(1L);

        assertEquals("test@example.com", result.getEmail());
        verify(userService).getASpecificUser(1L);
    }

    @Test
    void testDeleteASpecificUser() {
        User user = new User();
        user.setEmail("delete@example.com");

        doNothing().when(userService).deleteASpecificUser(user);

        ResponseEntity<String> response = userController.deleteASpecificUser(user, authentication);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("delete@example.com"));
        verify(userService).deleteASpecificUser(user);
    }

    @Test
    void testUpdateASpecificUser() {
        User user = new User();
        user.setEmail("update@example.com");

        doNothing().when(userService).updateASpecificUser(user);

        ResponseEntity<String> response = userController.updateASpecificUser(user, authentication);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("update@example.com"));
        verify(userService).updateASpecificUser(user);
    }
}
