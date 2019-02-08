package com.example.historical.security.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.historical.security.JwtTokenUtil;
import com.example.historical.security.JwtAccount;

@RestController
public class AccountRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtAccountDetailsService")
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "account", method = RequestMethod.GET)
    public JwtAccount getAuthenticatedAccount(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtAccount account = (JwtAccount) userDetailsService.loadUserByUsername(username);
        return account;
    }

}
