package br.com.v1.barber.dto.clientDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class ClientUpdateDto {
        private String name;

        private String date;

        private String CPF;

        private String phone;

        private String email;
}
