package com.cursojavanauta.create_user.infrastructure.dto;

import com.cursojavanauta.create_user.infrastructure.entity.Address;
import com.cursojavanauta.create_user.infrastructure.entity.Phone;
import lombok.Data;

import java.util.List;

@Data
public class UpdateUserForm {

    private String name;

    private String email;

    private List<Address> addresses;

    private List<Phone> phones;

}
