package br.com.v1.barber.dto.clientDto;

import br.com.v1.barber.domain.User;
import br.com.v1.barber.dto.userDto.UserDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
@Data
public class ClientDto extends UserDto {

}
