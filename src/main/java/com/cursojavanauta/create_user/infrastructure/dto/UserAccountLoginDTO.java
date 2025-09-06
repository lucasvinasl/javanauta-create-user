package com.cursojavanauta.create_user.infrastructure.dto;

import lombok.Data;

@Data
public class UserAccountLoginDTO {
    private String email;
    private String password;
}
