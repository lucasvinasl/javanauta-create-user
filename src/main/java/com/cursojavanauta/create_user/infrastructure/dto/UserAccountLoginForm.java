package com.cursojavanauta.create_user.infrastructure.dto;

import lombok.Data;

@Data
public class UserAccountLoginForm {
    private String email;
    private String password;
}
