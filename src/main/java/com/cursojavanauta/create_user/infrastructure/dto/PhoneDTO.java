package com.cursojavanauta.create_user.infrastructure.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhoneDTO {

    private Long id;

    private String phoneNumber;

    private String phoneCode;

}
