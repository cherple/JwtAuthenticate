package com.example.historical.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.historical.model.Account;
import com.example.historical.security.JwtAccountFactory;
import com.example.historical.repository.AccountRepository;

@Service
public class JwtAccountDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);

        if (account == null) {
            throw new UsernameNotFoundException(String.format("No account found with username '%s'.", username));
        } else {
            return JwtAccountFactory.create(account);
        }
    }
}
