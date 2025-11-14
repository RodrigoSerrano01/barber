package br.com.v1.barber.service.impl;

import br.com.v1.barber.domain.Client;
import br.com.v1.barber.domain.Employee;
import br.com.v1.barber.domain.Services;
import br.com.v1.barber.domain.WorkSchedule;
import br.com.v1.barber.dto.appointmentDto.AppointmentCreationDto;
import br.com.v1.barber.dto.appointmentDto.AppointmentUpdateDto;
import br.com.v1.barber.dto.employeeDto.EmployeeCreationDto;
import br.com.v1.barber.dto.employeeDto.EmployeeDto;
import br.com.v1.barber.dto.employeeDto.EmployeeUpdateDto;
import br.com.v1.barber.dto.mapping.ClientMapper;
import br.com.v1.barber.dto.mapping.EmployeeMapper;
import br.com.v1.barber.exception.handler.ClientAlreadyExistsException;
import br.com.v1.barber.exception.handler.EmployeeAlreadyExistsException;
import br.com.v1.barber.exception.handler.EmployeeNotFoundException;
import br.com.v1.barber.exception.handler.UserNotFoundException;
import br.com.v1.barber.repository.ClientRepository;
import br.com.v1.barber.repository.EmployeeRepository;
import br.com.v1.barber.repository.ServicesRepository;
import br.com.v1.barber.service.EmployeeService;
import br.com.v1.barber.util.ScheduleUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static br.com.v1.barber.enumerator.Error.*;
import static br.com.v1.barber.enumerator.Error.NO_CLIENT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository repository;
    private final ServicesRepository serviceRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public Employee findEmployeeById(String id) {
        log.info("Searching employee by id {}",id);
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(NO_EMPLOYEE_FOUND_BY_ID.getErrorDescription()));
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        log.info("Searching for employee");
        final List<Employee> employees = repository.findAll();
        if(employees.isEmpty()){
            log.info((NO_EMPLOYEE_FOUND.getErrorDescription()));
            throw new EmployeeNotFoundException(NO_EMPLOYEE_FOUND.getErrorDescription());
        }

        return employeeMapper.employeeListToEmployeeListDto(employees);
    }

    @Override
    public EmployeeDto createEmployee(EmployeeCreationDto employeeCreationDto) {
        log.info("Searching employee by name {}",employeeCreationDto.getName());
        employeeCreationDto.initSchedules();
        final Optional<Employee> employeeSearching = repository.findTopByNameEqualsIgnoreCase(employeeCreationDto.getName());
        if(employeeSearching.isPresent()){
            log.info(EMPLOYEE_ALREADY_REGISTERED.getErrorDescription());
            throw new EmployeeAlreadyExistsException(EMPLOYEE_ALREADY_REGISTERED.getErrorDescription());
        }
        employeeCreationDto.setPassword(encoder.encode(employeeCreationDto.getPassword()));
        final Employee employee = employeeMapper.employeeCreationDtoToEmployee(employeeCreationDto);

        repository.save(employee);
        log.info("Sucess registered{} ",employee.getName());
        return employeeMapper.employeeToEmployeeDto(employee);
    }

    @Override
    public void deleteEmployee(String id) {
        final Employee employee = this.findEmployeeById(id);
        repository.deleteById(id);
        log.info("Employee {} deleted",employee.getName());
    }

    @Override
    public EmployeeDto updateEmployee(String id, EmployeeUpdateDto employeeUpdateDto) {
         Employee existingEmployee = this.findEmployeeById(id);
        employeeMapper.employeeUpdateDtoToEmployee(employeeUpdateDto, existingEmployee);
        existingEmployee = createTimeSlots(existingEmployee);
        repository.save(existingEmployee);
        log.info("Success updated {}",existingEmployee.getName());
        return employeeMapper.employeeToEmployeeDto(existingEmployee);

    }

    @Override
    public EmployeeDto updateEmployeeSchedule(String id, EmployeeUpdateDto employeeUpdateDto) {
         Employee existingEmployee = this.findEmployeeById(id);

        employeeMapper.employeeUpdateDtoToEmployee(employeeUpdateDto, existingEmployee);


        repository.save(existingEmployee);
        log.info("Success updated schedule {}",existingEmployee.getName());
        return employeeMapper.employeeToEmployeeDto(existingEmployee);

    }


    /**
     *
     * @param employee
     *
     * create a list of 30min slots for everyday available for work
     *
     * @return
     */

    public Employee createTimeSlots(Employee employee){

    employee.getWorkSchedules().forEach(workSchedule -> {
            if (workSchedule.getWorking())
                workSchedule.setSlots(ScheduleUtils.generateHalfHourSlots(workSchedule.getStartTime(), workSchedule.getEndTime()));
           });

    return employee;
    }

    public void updateAvailableTimeSlot (Employee employee, AppointmentCreationDto appointmentCreationDto){

        final Optional<Services> service = serviceRepository.findById(appointmentCreationDto.getIdService());

        employee.getWorkSchedules().stream()
                .filter(ws -> ws.getWeekDay().equals(appointmentCreationDto.getWeekDay()))
                .filter(WorkSchedule::getWorking)
                .flatMap(ws -> ws.getSlots().stream())
                .filter(slot -> slot.getTime().equals(appointmentCreationDto.getHour()) && slot.isAvailable())
                .findFirst()
                .ifPresent(slot -> {
                    slot.setAvailable(false);
log.info("teste {} ",service.get().getServiceTime().getValue());
                    if (service.isPresent() && service.get().getServiceTime().getValue() == 2) {
                        employee.getWorkSchedules().stream()
                                .filter(ws -> ws.getWeekDay().equals(appointmentCreationDto.getWeekDay()))
                                .filter(WorkSchedule::getWorking)
                                .flatMap(ws -> ws.getSlots().stream())
                                .filter(nextSlot -> nextSlot.getTime().isAfter(slot.getTime()))
                                .findFirst()
                                .ifPresent(nextSlot -> nextSlot.setAvailable(false));
                    }
                });

        repository.save(employee);
    }
    public void updateDisableTimeSlot (Employee employee, AppointmentUpdateDto appointmentUpdateDto){

        Optional<Services> service = serviceRepository.findById(appointmentUpdateDto.getIdService());


        employee.getWorkSchedules().stream()
                .filter(ws -> ws.getWeekDay().equals(appointmentUpdateDto.getWeekDay()))
                .filter(WorkSchedule::getWorking)
                .flatMap(ws -> ws.getSlots().stream())
                .filter(slot -> slot.getTime().equals(appointmentUpdateDto.getHour()) && !slot.isAvailable())
                .findFirst()
                .ifPresent(slot -> {
                    // Libera o horário atual
                    slot.setAvailable(true);

                    // Se o serviço ocupar 2 slots, libera também o próximo
                    if (service.isPresent() && service.get().getServiceTime().getValue() == 2) {
                        employee.getWorkSchedules().stream()
                                .filter(ws -> ws.getWeekDay().equals(appointmentUpdateDto.getWeekDay()))
                                .filter(WorkSchedule::getWorking)
                                .flatMap(ws -> ws.getSlots().stream())
                                .filter(nextSlot -> nextSlot.getTime().equals(slot.getTime().plusMinutes(30)))
                                .findFirst()
                                .ifPresent(nextSlot -> nextSlot.setAvailable(true));
                    }
                });

        repository.save(employee);
    }

    public Employee findByEmail(String email) {
        return repository.findByEmail(email);

    }
}
