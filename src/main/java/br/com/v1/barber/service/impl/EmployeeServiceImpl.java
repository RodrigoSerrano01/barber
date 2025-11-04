package br.com.v1.barber.service.impl;

import br.com.v1.barber.domain.Client;
import br.com.v1.barber.domain.Employee;
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
import br.com.v1.barber.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        log.info("Searching employee by name",employeeCreationDto.getName());
        final Optional<Employee> employeeSearching = repository.findTopByNameEqualsIgnoreCase(employeeCreationDto.getName());
        if(employeeSearching.isPresent()){
            log.info(EMPLOYEE_ALREADY_REGISTERED.getErrorDescription());
            throw new EmployeeAlreadyExistsException(EMPLOYEE_ALREADY_REGISTERED.getErrorDescription());
        }
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
        final Employee existingEmployee = this.findEmployeeById(id);
        employeeMapper.employeeUpdateDtoToEmployee(employeeUpdateDto, existingEmployee);
        repository.save(existingEmployee);
        log.info("Success updated {}",existingEmployee.getName());
        return employeeMapper.employeeToEmployeeDto(existingEmployee);

    }
}
