package com.cursojavanauta.create_user.infrastructure.dto;

import lombok.Data;

@Data
public class AddressDTO {

    private Long id;

    private String street;

    private Long number;

    private String complement;

    private String city;

    private String state;

    private String uf;

    private String zipCode;

    private String country;
}
