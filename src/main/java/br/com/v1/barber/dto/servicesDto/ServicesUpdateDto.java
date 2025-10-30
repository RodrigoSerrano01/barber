package br.com.v1.barber.dto.servicesDto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicesUpdateDto {

    @NotBlank
    private String name;
    @NotNull
    private Float value;
}
