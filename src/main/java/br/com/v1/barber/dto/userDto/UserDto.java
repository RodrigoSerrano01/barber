package br.com.v1.barber.dto.userDto;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private String email;
}
