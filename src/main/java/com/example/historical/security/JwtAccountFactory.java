package com.example.historical.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.example.historical.model.Authority;
import com.example.historical.model.Account;

public final class JwtAccountFactory {

    private JwtAccountFactory() {
    }

    public static JwtAccount create(Account account) {
        return new JwtAccount(
        		account.getId(),
        		account.getLiveId(),
        		account.getUsername(),
        		account.getFirstname(),
        		account.getLastname(),
        		account.getEmail(),
        		account.getPassword(),
                mapToGrantedAuthorities(account.getAuthorities()),
                account.getEnabled(),
                account.getLastPasswordResetDate()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toList());
    }
}
