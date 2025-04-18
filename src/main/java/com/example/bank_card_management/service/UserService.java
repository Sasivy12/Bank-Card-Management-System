package com.example.bank_card_management.service;

import com.example.bank_card_management.model.User;
import com.example.bank_card_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService
{
    private final UserRepository userRepository;

    private final AuthenticationManager authManager;

    private final JwtService jwtService;

    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }
}
