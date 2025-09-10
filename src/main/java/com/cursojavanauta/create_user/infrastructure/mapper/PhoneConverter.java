package com.cursojavanauta.create_user.infrastructure.mapper;

import com.cursojavanauta.create_user.infrastructure.dto.PhoneDTO;
import com.cursojavanauta.create_user.infrastructure.entity.Phone;
import org.springframework.stereotype.Component;

@Component
public class PhoneConverter {

    public Phone toEntityFromDTO(PhoneDTO dto) {
        return Phone.builder()
                .id(dto.getId())
                .phoneCode(dto.getPhoneCode())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }

    public PhoneDTO toDTOFromEntity(Phone phone) {
        return PhoneDTO.builder()
                .id(phone.getId())
                .phoneCode(phone.getPhoneCode())
                .phoneNumber(phone.getPhoneNumber())
                .build();
    }

}

/*
    * @Builder do Lombok serve para fazer um Mapper também.

    * Não é obrigado a colocar @Builder em tudo,só onde faz sentido para o jeito que você vai converter.

    * Se só precisa montar entities a partir do DTO (ex.: no toEntityFromDTO), basta colocar o @Builder na entity:
        Phone.builder() -> habilitado com @Builder
    * Se só precisa montar DTOs a partir da entity (ex.: no toDTOFromEntity), basta colocar @Builder no DTO:
        PhoneDTO.builder() -> habilitado com @Builder

    * Não necessariamente eu preciso usar @Component no Converter, caso não use, eu declaro os métodos como static e
    então não é necessário fazer a injeção da classe nos services. usa direto na declaração da classe:
        UserAccount entity = UserAccountConverter.toEntity(dto);

 */
