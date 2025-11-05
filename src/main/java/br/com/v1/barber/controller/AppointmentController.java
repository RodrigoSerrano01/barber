package br.com.v1.barber.controller;


import br.com.v1.barber.dto.appointmentDto.AppointmentCreationDto;
import br.com.v1.barber.dto.appointmentDto.AppointmentDto;
import br.com.v1.barber.dto.appointmentDto.AppointmentUpdateDto;
import br.com.v1.barber.dto.clientDto.ClientCreationDto;
import br.com.v1.barber.dto.clientDto.ClientDto;
import br.com.v1.barber.dto.clientDto.ClientUpdateDto;
import br.com.v1.barber.service.impl.AppointmentServiceImpl;
import br.com.v1.barber.service.impl.ClientServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AppointmentController extends RootController{
    private final AppointmentServiceImpl service;


    @GetMapping(path ="/appointment")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentDto> getAllAppointment(){
        return service.getAllAppointment();
    }

    @PostMapping(path ="/appointment/add")
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentDto createAppointment (@Valid @RequestBody AppointmentCreationDto appointmentCreationDto){
        return service.createAppointment(appointmentCreationDto);
    }

    @DeleteMapping(path ="/appointment/{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public void deleteAppointment (@PathVariable String id){
        service.deleteAppointment(id);
    }

    @PutMapping("appointment/{id}/update")
    public AppointmentDto updadteClient ( @PathVariable String id,@Valid @RequestBody AppointmentUpdateDto updatedAppointment){
        return service.updateAppointment(id, updatedAppointment);
    }

}
