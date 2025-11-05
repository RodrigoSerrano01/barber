package br.com.v1.barber.service;

import br.com.v1.barber.domain.Appointment;
import br.com.v1.barber.dto.appointmentDto.AppointmentCreationDto;
import br.com.v1.barber.dto.appointmentDto.AppointmentDto;
import br.com.v1.barber.dto.appointmentDto.AppointmentUpdateDto;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public interface AppointmentService {


    public Appointment findAppointmentById (String id);
    public List<AppointmentDto> getAllAppointment();
    public AppointmentDto createAppointment(AppointmentCreationDto appointmentCreationDto);
    public void deleteAppointment (String id);
    public AppointmentDto updateAppointment (String id, AppointmentUpdateDto appointmentUpdateDto);
}
