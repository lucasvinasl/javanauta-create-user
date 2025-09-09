package com.cursojavanauta.create_user.business.service;

import com.cursojavanauta.create_user.infrastructure.dto.TokenDTO;
import com.cursojavanauta.create_user.infrastructure.exception.SelfLoginAuthException;
import com.cursojavanauta.create_user.infrastructure.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthenticationService(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public Authentication auth(String login, String password) {
        try{
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login, password);
            return authenticationManager.authenticate(token);
        } catch (AuthenticationException e) {
            throw new SelfLoginAuthException(e.getMessage());
        }
    }

    public TokenDTO getToken(Authentication authentication) {
        String token = jwtUtil.generateToken(authentication.getName());
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setType("Bearer");
        tokenDTO.setToken(token);
        return tokenDTO;
    }



}
