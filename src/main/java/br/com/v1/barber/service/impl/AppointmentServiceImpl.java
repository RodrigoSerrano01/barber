package br.com.v1.barber.service.impl;

import br.com.v1.barber.domain.Appointment;
import br.com.v1.barber.domain.Client;
import br.com.v1.barber.domain.Employee;
import br.com.v1.barber.domain.Services;
import br.com.v1.barber.dto.appointmentDto.AppointmentCreationDto;
import br.com.v1.barber.dto.appointmentDto.AppointmentDto;
import br.com.v1.barber.dto.appointmentDto.AppointmentUpdateDto;
import br.com.v1.barber.dto.mapping.AppointmentMapper;
import br.com.v1.barber.exception.handler.AppointmentNotFoundException;
import br.com.v1.barber.repository.AppointmentRepository;
import br.com.v1.barber.repository.ClientRepository;
import br.com.v1.barber.repository.EmployeeRepository;
import br.com.v1.barber.repository.ServicesRepository;
import br.com.v1.barber.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import static br.com.v1.barber.enumerator.Error.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentMapper appointmentMapper;
    private final AppointmentRepository repository;

    private final AppointmentRepository appointmentRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final ServicesRepository serviceRepository;

    @Override
    public Appointment findAppointmentById(String id) {
        log.info("Searching appointment by id {}",id);
        return repository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(NO_APPOINTMENT_FOUND_BY_ID.getErrorDescription()));
    }

    @Override
    public List<AppointmentDto> getAllAppointment() {
        log.info("Searching for Appointment");
        final List<Appointment> appointmentList = repository.findAll();
        if(appointmentList.isEmpty()){
            log.info((NO_APPOINTMENT_FOUND.getErrorDescription()));
            throw new AppointmentNotFoundException(NO_APPOINTMENT_FOUND.getErrorDescription());
        }
        return appointmentMapper.appointmentListToAppointmentListDto(appointmentList);
    }

    @Override
    public AppointmentDto createAppointment(AppointmentCreationDto appointmentCreationDto) {



        final Appointment appointment = appointmentMapper.appointmentCreationDtoToAppointment(appointmentCreationDto);
        //validationHour(appointment);
        repository.save(appointment);

        log.info("Sucess registered ");
        return appointmentMapper.appointmentToAppointmentDto(appointment);
    }

//    public void validationHour (Appointment appointment){
//
////        Optional<Client> client = clientRepository.findById(appointmentCreationDto.getIdClient());
////        Optional<Employee> employee = employeeRepository.findById(appointmentCreationDto.getIdEmployee());
////        Optional<Services> service = serviceRepository.findById(appointmentCreationDto.getIdService());
//        ap
//
//
//    }

    @Override
    public void deleteAppointment(String id) {
        repository.deleteById(id);
        log.info("Appointment deleted");
    }

    @Override
    public AppointmentDto updateAppointment(String id, AppointmentUpdateDto appointmentUpdateDto) {
        final Appointment existingAppointment = this.findAppointmentById(id);
        appointmentMapper.appointmentUpdateDtoToAppointment(appointmentUpdateDto, existingAppointment);
        repository.save(existingAppointment);
        log.info("Success updated ");
        return appointmentMapper.appointmentToAppointmentDto(existingAppointment);
    }
}
