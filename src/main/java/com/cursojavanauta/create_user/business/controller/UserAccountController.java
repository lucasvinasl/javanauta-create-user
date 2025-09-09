package com.cursojavanauta.create_user.business.controller;

import com.cursojavanauta.create_user.business.service.AuthenticationService;
import com.cursojavanauta.create_user.business.service.UserAccountService;
import com.cursojavanauta.create_user.infrastructure.dto.CreateUserForm;
import com.cursojavanauta.create_user.infrastructure.dto.TokenDTO;
import com.cursojavanauta.create_user.infrastructure.dto.UserAccountDTO;
import com.cursojavanauta.create_user.infrastructure.dto.UserAccountLoginForm;
import com.cursojavanauta.create_user.infrastructure.entity.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountService userAccountService;
    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<UserAccountDTO> create(@RequestBody CreateUserForm form){
        return ResponseEntity.ok(userAccountService.create(form));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody UserAccountLoginForm loginDTO){
        Authentication authentication = authenticationService.auth(loginDTO.getEmail(),  loginDTO.getPassword());
        return ResponseEntity.ok(authenticationService.getToken(authentication));
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
