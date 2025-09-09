package com.cursojavanauta.create_user.business.service;

import com.cursojavanauta.create_user.infrastructure.dto.UserAccountDTO;
import com.cursojavanauta.create_user.infrastructure.entity.UserAccount;
import com.cursojavanauta.create_user.infrastructure.exception.SelfConflictException;
import com.cursojavanauta.create_user.infrastructure.mapper.UserAccountMapper;
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
    private final UserAccountMapper userAccountMapper;

    @Transactional
    public UserAccountDTO create(UserAccountDTO dto){
        try{
            UserAccount user = userAccountMapper.toEntityFromDTO(dto);
            handleExistsEmail(user.getEmail());
            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);
            user = userAccountRepository.save(user);
            return userAccountMapper.toDTOFromEntity(user);
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
