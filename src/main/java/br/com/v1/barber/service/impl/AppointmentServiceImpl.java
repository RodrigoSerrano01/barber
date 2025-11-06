package br.com.v1.barber.service.impl;

import br.com.v1.barber.domain.*;
import br.com.v1.barber.dto.appointmentDto.AppointmentCreationDto;
import br.com.v1.barber.dto.appointmentDto.AppointmentDto;
import br.com.v1.barber.dto.appointmentDto.AppointmentUpdateDto;
import br.com.v1.barber.dto.employeeDto.EmployeeDto;
import br.com.v1.barber.dto.employeeDto.EmployeeUpdateDto;
import br.com.v1.barber.dto.mapping.AppointmentMapper;
import br.com.v1.barber.dto.mapping.EmployeeMapper;
import br.com.v1.barber.enumerator.WeekDay;
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
import static java.util.spi.ToolProvider.findFirst;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentMapper appointmentMapper;
    private final AppointmentRepository repository;
    private final EmployeeMapper employeeMapper;
    private final EmployeeServiceImpl employeeService;

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


        final Optional<Services> service = serviceRepository.findById(appointmentCreationDto.getIdService());


        if (validationHour(appointmentCreationDto) && service.isPresent()) {
            log.info("teste {} ",service.get().getServiceTime());
            int aux=0;
            Employee existingEmployee = employeeService.findEmployeeById(appointmentCreationDto.getIdEmployee());
            do {
                employeeService.updateAvailableTimeSlot(existingEmployee, appointmentCreationDto);
                aux++;
            }while(aux<service.get().getServiceTime().getValue());
            final Appointment appointment = appointmentMapper.appointmentCreationDtoToAppointment(appointmentCreationDto);
            employeeRepository.save(existingEmployee);
            repository.save(appointment);

            log.info("Sucess registered ");
            return appointmentMapper.appointmentToAppointmentDto(appointment);
        }
        log.info((NO_APPOINTMENT_FOUND.getErrorDescription()));
        throw new AppointmentNotFoundException(NO_APPOINTMENT_FOUND.getErrorDescription());
    }

    public Boolean validationHour (AppointmentCreationDto appointmentCreationDto){

        Optional<Client> client = clientRepository.findById(appointmentCreationDto.getIdClient());
        Optional<Employee> employee = employeeRepository.findById(appointmentCreationDto.getIdEmployee());
        Optional<Services> service = serviceRepository.findById(appointmentCreationDto.getIdService());

        if(client.isPresent()&&employee.isPresent()&&service.isPresent()){
           return employee.get().getWorkSchedules().stream()
                    .filter(ws -> ws.getWeekDay().equals(appointmentCreationDto.getWeekDay()))
                    .filter(WorkSchedule::getWorking)
                    .flatMap(ws -> ws.getSlots().stream())
                    .anyMatch(slot -> slot.getTime().equals(appointmentCreationDto.getHour()) && slot.isAvailable());
        }
        log.info((NO_APPOINTMENT_FOUND.getErrorDescription()));
        throw new AppointmentNotFoundException(NO_APPOINTMENT_FOUND.getErrorDescription());

    }

    @Override
    public void deleteAppointment(String id) {
        final Appointment existingAppointment = this.findAppointmentById(id);
        final AppointmentUpdateDto appointmentUpdateDto  = appointmentMapper.appointmentToAppointmentUpdateDto(existingAppointment);

        Employee existingEmployee = employeeService.findEmployeeById(appointmentUpdateDto.getIdEmployee());
        employeeService.updateDisableTimeSlot(existingEmployee,appointmentUpdateDto);

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
