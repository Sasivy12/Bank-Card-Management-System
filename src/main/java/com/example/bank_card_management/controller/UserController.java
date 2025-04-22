package com.example.bank_card_management.controller;

import com.example.bank_card_management.model.User;
import com.example.bank_card_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController
{
    private final UserService userService;

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getASpecificUser(@PathVariable Long userId)
    {
        return userService.getASpecificUser(userId);
    }

    @DeleteMapping()
    @PreAuthorize("hasRole('ADMIN') or #auth.name == @userService.getEmailByCardId(#request.cardId)")
    public ResponseEntity<String> deleteASpecificUser(@RequestBody User user, Authentication auth)
    {
        userService.deleteASpecificUser(user);

        return ResponseEntity.ok("User " + user.getEmail() + " successfully deleted");
    }

    @PutMapping()
    @PreAuthorize("hasRole('ADMIN') or #auth.name == @userService.getEmailByCardId(#request.cardId)")
    public ResponseEntity<String> updateASpecificUser(@RequestBody User user, Authentication auth)
    {
        userService.updateASpecificUser(user);

        return ResponseEntity.ok("User " + user.getEmail() + " successfully updated");
    }
}
