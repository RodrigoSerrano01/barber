package br.com.v1.barber.dto.userDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {

    private String name;

    private String date;

    private String CPF;

    private String phone;

    private String email;
}
