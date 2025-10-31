package br.com.v1.barber.dto.clientDto;

import br.com.v1.barber.dto.userDto.UserCreationDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
public class ClientCreationDto extends UserCreationDto {

}
