package br.com.v1.barber.dto.authDto;

import lombok.Data;

@Data
public class AuthRequest {

    private String email;
    private String password;
}
