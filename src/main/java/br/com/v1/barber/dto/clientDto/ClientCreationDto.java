package br.com.v1.barber.dto.clientDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientCreationDto {
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
