package com.cursojavanauta.create_user.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private Long number;

    private String complement;

    private String city;

    private String state;

    private String uf;

    @Column(length = 9, nullable = false)
    private String zipCode;

    private String country;

}
