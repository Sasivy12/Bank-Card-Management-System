package com.example.bank_card_management.service;

import com.example.bank_card_management.model.User;
import com.example.bank_card_management.model.UserDetailsImpl;
import com.example.bank_card_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService
{
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        User user = userRepository.findByEmail(email);

        if(user == null)
        {
            throw new UsernameNotFoundException("User not found");
        }
        else
        {
            return new UserDetailsImpl(user);
        }
    }
}
