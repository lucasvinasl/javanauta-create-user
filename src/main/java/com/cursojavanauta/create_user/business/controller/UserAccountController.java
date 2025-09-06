package com.cursojavanauta.create_user.business.controller;

import com.cursojavanauta.create_user.business.service.UserAccountService;
import com.cursojavanauta.create_user.infrastructure.dto.UserAccountLoginDTO;
import com.cursojavanauta.create_user.infrastructure.entity.UserAccount;
import com.cursojavanauta.create_user.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountService userAccountService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<UserAccount> create(@RequestBody UserAccount userAccount){
        return ResponseEntity.ok(userAccountService.create(userAccount));
    }

    @PostMapping("/login")
    public String login(@RequestBody UserAccountLoginDTO loginDTO){
        Authentication  authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }

    @GetMapping
    public ResponseEntity<UserAccount> getUserAccount(@RequestParam String email){
        return ResponseEntity.ok().body(userAccountService.findByEmail(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAccount> getUserAccount(@PathVariable Long id){
        return ResponseEntity.ok().body(userAccountService.findById(id));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteByEmail(@PathVariable String email){
        userAccountService.deleteByEmail(email);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(@RequestParam Long id){
        userAccountService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
