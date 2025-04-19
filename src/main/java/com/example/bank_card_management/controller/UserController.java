package com.example.bank_card_management.controller;

import com.example.bank_card_management.model.User;
import com.example.bank_card_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController
{
    private final UserService userService;

    @GetMapping()
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getASpecificUser(@PathVariable Long userId)
    {
        return userService.getASpecificUser(userId);
    }
}
