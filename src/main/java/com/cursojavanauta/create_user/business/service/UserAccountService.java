package com.cursojavanauta.create_user.business.service;

import com.cursojavanauta.create_user.infrastructure.dto.CreateUserForm;
import com.cursojavanauta.create_user.infrastructure.dto.UpdateUserForm;
import com.cursojavanauta.create_user.infrastructure.dto.UserAccountDTO;
import com.cursojavanauta.create_user.infrastructure.entity.Address;
import com.cursojavanauta.create_user.infrastructure.entity.UserAccount;
import com.cursojavanauta.create_user.infrastructure.exception.SelfEntityConflictException;
import com.cursojavanauta.create_user.infrastructure.exception.SelfEntityNotFound;
import com.cursojavanauta.create_user.infrastructure.mapper.UserAccountMapper;
import com.cursojavanauta.create_user.infrastructure.repository.UserAccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public UserAccountDTO update(UpdateUserForm form){
        UserAccount user = getAuthenticatedUser();
        if(user == null){
            throw new SelfEntityNotFound("Usuário não autenticado.");
        }

        userAccountMapper.updateEntityFromUpdateForm(form, user);
        if(form.getAddresses() != null && !form.getAddresses().isEmpty()){
            List<Address> newAddresses = new ArrayList<>();
            for(Address address : form.getAddresses()){
                Address newAddress = getNewAddress(address);
                newAddresses.add(newAddress);
            }
            user.setAddresses(newAddresses);
        }
        userAccountRepository.save(user);
        return  userAccountMapper.toDTOFromEntity(user);
    }

    private Address getNewAddress(Address address) {
        Address newAddress = new Address();
        newAddress.setStreet(address.getStreet() != null ?  address.getStreet() : null);
        newAddress.setNumber(address.getNumber() != null ?  address.getNumber() : null);
        newAddress.setComplement(address.getComplement() != null ?  address.getComplement() : null);
        newAddress.setCity(address.getCity() != null ?  address.getCity() : null);
        newAddress.setState(address.getState() != null ?  address.getState() : null);
        newAddress.setUf(address.getUf() != null ?  address.getUf() : null);
        newAddress.setZipCode(address.getZipCode() != null ?  address.getZipCode() : null);
        newAddress.setCountry(address.getCountry() != null ?  address.getCountry() : null);
        //adicionar o repository do address
        return newAddress;
    }

    public UserAccount getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated()){
            var principal =authentication.getPrincipal();
            if(principal instanceof UserAccount userAccount){
                return userAccount;
            }
        }
        return null;
    }



}
