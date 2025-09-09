package com.cursojavanauta.create_user.infrastructure.mapper;

import com.cursojavanauta.create_user.infrastructure.dto.CreateUserForm;
import com.cursojavanauta.create_user.infrastructure.dto.UserAccountDTO;
import com.cursojavanauta.create_user.infrastructure.entity.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserAccountMapper {

    @Mapping(target = "id", ignore = true)
    UserAccount toEntityFromDTO(UserAccountDTO dto);

    UserAccountDTO toDTOFromEntity(UserAccount entity);

    @Mapping(target = "id", ignore = true)
    UserAccount toEntityFromForm(CreateUserForm form);
}
