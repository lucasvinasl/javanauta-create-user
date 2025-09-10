package com.cursojavanauta.create_user.infrastructure.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserAccountDTO {
    private Long id;
    private String name;
    private String email;
    private List<AddressDTO> addresses;
    private List<PhoneDTO> phones;

}
