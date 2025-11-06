package br.com.v1.barber.domain;


import br.com.v1.barber.enumerator.WeekDay;
import br.com.v1.barber.validation.DateDDMMYYYY;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Document(collection = "appointment")
public class Appointment {

    @Id
    private String id;
    @NotBlank
    private String idClient;
    @NotBlank
    private String idEmployee;
    @NotBlank
    private String idService;
    @NotBlank
    private WeekDay weekDay;
    @DateDDMMYYYY
    private String date;
    @NotBlank
    private LocalTime  hour;
}
