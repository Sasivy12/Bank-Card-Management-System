package com.example.bank_card_management.service;

import com.example.bank_card_management.model.BankCard;
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

    public User getASpecificUser(Long userId)
    {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User with id " + userId + " not found"));

        return user;
    }

    public void deleteASpecificUser(User user)
    {
        userRepository.delete(user);
    }

    public void updateASpecificUser(User updatedUser)
    {
        User existingUser = userRepository.findById(updatedUser.getId())
                .orElseThrow(() -> new RuntimeException("User with id " + updatedUser.getId() + " not found"));

        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setFullName(updatedUser.getFullName());
        existingUser.setStatus(updatedUser.getStatus());
        existingUser.setRole(updatedUser.getRole());

        existingUser.getCards().clear();
        if (updatedUser.getCards() != null)
        {
            for (BankCard card : updatedUser.getCards())
            {
                card.setCardHolder(existingUser);
                existingUser.getCards().add(card);
            }
        }

        userRepository.save(existingUser);
    }
}
