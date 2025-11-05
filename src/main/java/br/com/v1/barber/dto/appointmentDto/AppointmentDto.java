package br.com.v1.barber.dto.appointmentDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AppointmentDto {

    @NotBlank
    private String id;
    @NotBlank
    private String idClient;
    @NotBlank
    private String idEmployee;
    @NotBlank
    private String idService;
}
