package br.com.v1.barber.dto.clientDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
@Data
public class ClientDto {
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
