package com.cursojavanauta.create_user.infrastructure.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateUserForm {
    private String name;
    private String email;
    private String password;
    private List<AddressDTO> addresses;
    private List<PhoneDTO> phones;
}
