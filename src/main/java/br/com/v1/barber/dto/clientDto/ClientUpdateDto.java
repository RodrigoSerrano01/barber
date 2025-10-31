package br.com.v1.barber.dto.clientDto;

import br.com.v1.barber.dto.userDto.UserUpdateDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
    public class ClientUpdateDto extends UserUpdateDto {

}
