package br.com.v1.barber.dto.appointmentDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentUpdateDto {


    @NotBlank
    private String idClient;
    @NotBlank
    private String idEmployee;
    @NotBlank
    private String idService;
}
