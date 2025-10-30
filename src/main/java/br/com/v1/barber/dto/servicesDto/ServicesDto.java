package br.com.v1.barber.dto.servicesDto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ServicesDto {
    @NotBlank
    private String id;
    @NotBlank
    private String name;
    @NotNull
    private Float value;
}
