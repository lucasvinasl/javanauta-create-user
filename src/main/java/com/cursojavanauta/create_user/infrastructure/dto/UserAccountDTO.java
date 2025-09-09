package com.cursojavanauta.create_user.infrastructure.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserAccountDTO {

    private String name;
    private String email;
    private List<AddressDTO> addresses;
    private List<PhoneDTO> phones;

}
