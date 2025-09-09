package com.cursojavanauta.create_user.infrastructure.dto;

import lombok.Data;

import java.util.List;

@Data
public class TokenDTO {
    private String type;
    private String token;
}
