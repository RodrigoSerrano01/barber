package br.com.v1.barber.dto.authDto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class AuthRequest {
    @Email
    private String email;
    private String password;
}
