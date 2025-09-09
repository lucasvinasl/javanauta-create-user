package com.cursojavanauta.create_user.business.service;

import com.cursojavanauta.create_user.infrastructure.dto.CreateUserForm;
import com.cursojavanauta.create_user.infrastructure.dto.UserAccountDTO;
import com.cursojavanauta.create_user.infrastructure.entity.UserAccount;
import com.cursojavanauta.create_user.infrastructure.exception.SelfEntityConflictException;
import com.cursojavanauta.create_user.infrastructure.exception.SelfEntityNotFound;
import com.cursojavanauta.create_user.infrastructure.mapper.UserAccountMapper;
import com.cursojavanauta.create_user.infrastructure.repository.UserAccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserAccountMapper userAccountMapper;

    @Transactional
    public UserAccountDTO create(CreateUserForm form){
        try{
            UserAccount user = userAccountMapper.toEntityFromForm(form);
            handleExistsEmail(user.getEmail());
            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);
            user = userAccountRepository.save(user);
            return userAccountMapper.toDTOFromEntity(user);
        }catch (SelfEntityConflictException e){
            throw new SelfEntityConflictException(e.getMessage());
        }
    }

    private void handleExistsEmail(String email){
        try{
            if(existsByEmail(email)){
                throw new SelfEntityConflictException("Email já cadastrado para outro usuário.");
            }
        } catch (SelfEntityConflictException e) {
            throw new SelfEntityConflictException("Email já cadastrado.", e.getCause());
        }
    }

    private boolean existsByEmail(String email){
        return userAccountRepository.existsByEmail(email);
    }

    public UserAccount findByEmail(String email){
        return userAccountRepository.findByEmail(email).orElseThrow(() -> new SelfEntityNotFound("Email não existe."));
    }

    public UserAccount findById(Long id){
        return userAccountRepository.findById(id).orElseThrow(() -> new SelfEntityNotFound("Usuário não encontrado."));
    }

    @Transactional
    public void deleteByEmail(String email){
        UserAccount userAccount = findByEmail(email);
        userAccountRepository.delete(userAccount);
    }

    @Transactional
    public void deleteById(Long id){
        UserAccount userAccount = userAccountRepository.findById(id)
                .orElseThrow(() -> new SelfEntityNotFound("Usuário não encontrado."));

        userAccountRepository.delete(userAccount);
    }



}
