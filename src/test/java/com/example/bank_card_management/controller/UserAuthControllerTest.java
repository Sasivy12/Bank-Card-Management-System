package com.example.bank_card_management.controller;

import com.example.bank_card_management.dto.LoginRequest;
import com.example.bank_card_management.model.User;
import com.example.bank_card_management.service.UserAuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserAuthControllerTest
{

    @Mock
    private UserAuthService userService;

    @InjectMocks
    private UserAuthController userAuthController;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister()
    {
        User user = new User();
        user.setEmail("test@example.com");

        doNothing().when(userService).register(user);

        ResponseEntity<String> response = userAuthController.register(user);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("test@example.com"));
        verify(userService, times(1)).register(user);
    }

    @Test
    void testLogin()
    {
        LoginRequest request = new LoginRequest();
        request.setEmail("test@example.com");
        request.setPassword("password");

        when(userService.verify(request)).thenReturn("jwt-token");

        String result = userAuthController.login(request);

        assertEquals("jwt-token", result);
        verify(userService, times(1)).verify(request);
    }
}
