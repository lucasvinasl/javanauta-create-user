package com.cursojavanauta.create_user.infrastructure.mapper;

import com.cursojavanauta.create_user.infrastructure.dto.AddressDTO;
import com.cursojavanauta.create_user.infrastructure.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AddressMapper {
    
    Address toEntityFromDTO(AddressDTO dto);
    AddressDTO toDTOFromEntity(Address address);

}
