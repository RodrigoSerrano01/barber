package br.com.v1.barber.service.impl;

import br.com.v1.barber.domain.Client;
import br.com.v1.barber.domain.Employee;
import br.com.v1.barber.dto.employeeDto.EmployeeCreationDto;
import br.com.v1.barber.dto.employeeDto.EmployeeDto;
import br.com.v1.barber.dto.employeeDto.EmployeeUpdateDto;
import br.com.v1.barber.dto.mapping.ClientMapper;
import br.com.v1.barber.dto.mapping.EmployeeMapper;
import br.com.v1.barber.exception.handler.EmployeeNotFoundException;
import br.com.v1.barber.exception.handler.UserNotFoundException;
import br.com.v1.barber.repository.ClientRepository;
import br.com.v1.barber.repository.EmployeeRepository;
import br.com.v1.barber.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.v1.barber.enumerator.Error.NO_CLIENT_FOUND_BY_ID;
import static br.com.v1.barber.enumerator.Error.NO_EMPLOYEE_FOUND_BY_ID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository repository;

    @Override
    public Employee findEmployeeById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(NO_EMPLOYEE_FOUND_BY_ID.getErrorDescription()));
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        final List<Employee> employees = repository.findAll();

        return employeeMapper.employeeListToEmployeeListDto(employees);
    }

    @Override
    public EmployeeDto createEmployee(EmployeeCreationDto employeeCreationDto) {
        final Employee employee = employeeMapper.employeeCreationDtoToEmployee(employeeCreationDto);
        repository.save(employee);

        return employeeMapper.employeeToEmployeeDto(employee);
    }

    @Override
    public void deleteEmployee(String id) {
        repository.deleteById(id);
    }

    @Override
    public EmployeeDto updateEmployee(String id, EmployeeUpdateDto employeeUpdateDto) {
        final Employee existingEmployee = this.findEmployeeById(id);
        employeeMapper.employeeUpdateDtoToEmployee(employeeUpdateDto, existingEmployee);
        repository.save(existingEmployee);
        return employeeMapper.employeeToEmployeeDto(existingEmployee);

    }
}
