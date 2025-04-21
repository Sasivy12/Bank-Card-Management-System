package com.example.bank_card_management.service;

import com.example.bank_card_management.exception.BankCardNotFoundException;
import com.example.bank_card_management.exception.UserNotFoundException;
import com.example.bank_card_management.model.BankCard;
import com.example.bank_card_management.model.User;
import com.example.bank_card_management.repository.BankCardRepository;
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

    private final BankCardRepository bankCardRepository;

    private final AuthenticationManager authManager;

    private final JwtService jwtService;

    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    public User getASpecificUser(Long userId)
    {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User with id " + userId + " not found"));

        return user;
    }

    public void deleteASpecificUser(User user)
    {
        userRepository.delete(user);
    }

    public void updateASpecificUser(User updatedUser)
    {
        User existingUser = userRepository.findById(updatedUser.getId())
                .orElseThrow(() -> new UserNotFoundException("User with id " + updatedUser.getId() + " not found"));

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

    public String getEmailByCardId(Long cardId)
    {
        BankCard card = bankCardRepository.findById(cardId)
                .orElseThrow(() -> new BankCardNotFoundException("Card not found"));

        return card.getCardHolder().getEmail();
    }
}
