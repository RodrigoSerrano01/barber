package br.com.v1.barber.dto.appointmentDto;

import br.com.v1.barber.enumerator.WeekDay;
import br.com.v1.barber.validation.DateDDMMYYYY;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

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

    private WeekDay weekDay;
    @DateDDMMYYYY
    private String date;

    private LocalTime  hour;
}
