package com.example.bank_card_management.controller;

import com.example.bank_card_management.dto.LoginRequest;
import com.example.bank_card_management.model.User;
import com.example.bank_card_management.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping()
public class UserAuthController
{
    private final UserAuthService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user)
    {
        userService.register(user);

        return ResponseEntity.ok("User " + user.getEmail() + " registered successfully");
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest)
    {
        return userService.verify(loginRequest);
    }
}
