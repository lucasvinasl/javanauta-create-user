package com.cursojavanauta.create_user.business.service;

import com.cursojavanauta.create_user.infrastructure.entity.UserAccount;
import com.cursojavanauta.create_user.infrastructure.exception.SelfConflictException;
import com.cursojavanauta.create_user.infrastructure.repository.UserAccountRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserAccount create(UserAccount userAccount){
        try{
            handleExistsEmail(userAccount.getEmail());
            String encryptedPassword = passwordEncoder.encode(userAccount.getPassword());
            userAccount.setPassword(encryptedPassword);
            return userAccountRepository.save(userAccount);
        }catch (SelfConflictException e){
            throw new SelfConflictException(e.getMessage());
        }
    }

    private void handleExistsEmail(String email){
        try{
            if(existsByEmail(email)){
                throw new SelfConflictException("Email já cadastrado para outro usuário.");
            }
        } catch (SelfConflictException e) {
            throw new SelfConflictException("Email já cadastrado.", e.getCause());
        }
    }

    private boolean existsByEmail(String email){
        return userAccountRepository.existsByEmail(email);
    }

    public UserAccount findByEmail(String email){
        return userAccountRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Email não existe."));
    }

    public UserAccount findById(Long id){
        return userAccountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));
    }

    @Transactional
    public void deleteByEmail(String email){
        UserAccount userAccount = findByEmail(email);
        userAccountRepository.delete(userAccount);
    }

    @Transactional
    public void deleteById(Long id){
        UserAccount userAccount = userAccountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));

        userAccountRepository.delete(userAccount);
    }



}
