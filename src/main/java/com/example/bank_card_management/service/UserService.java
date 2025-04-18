package com.example.bank_card_management.service;

import com.example.bank_card_management.model.User;
import com.example.bank_card_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService
{
    private final UserRepository userRepository;

    private final AuthenticationManager authManager;

    private final JwtService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public void register(User user)
    {
        if(userRepository.existsByEmail(user.getEmail()))
        {
            throw new RuntimeException("User with this email: " + user.getEmail() + " already exists");
        }
        else
        {
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
        }
    }

    public String verify(User user)
    {
        try
        {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

            if (authentication.isAuthenticated())
            {
                return jwtService.generateToken(user.getEmail());
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException("Authentication failed for user: " + user.getEmail());
        }
        return "FAILED";
    }

}
