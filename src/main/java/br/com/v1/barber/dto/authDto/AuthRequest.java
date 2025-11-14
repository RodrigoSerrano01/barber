package br.com.v1.barber.dto.authDto;

import br.com.v1.barber.enumerator.UserRole;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class AuthRequest {
    @Email
    private String email;
    private String password;
    private UserRole userRole;
}
