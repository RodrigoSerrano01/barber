package br.com.v1.barber.dto.userDto;

import br.com.v1.barber.enumerator.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public abstract class UserDto {

    @NotBlank
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String date;
    @NotBlank
    private String CPF;
    @NotBlank
    private String phone;
    @Email
    private String email;
    @NotBlank
    @Size(max =50)
    private String password;
    @NotBlank
    private UserRole userRole;
}
