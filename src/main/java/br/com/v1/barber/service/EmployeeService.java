package br.com.v1.barber.service;

import br.com.v1.barber.domain.Employee;
import br.com.v1.barber.domain.Services;
import br.com.v1.barber.dto.employeeDto.EmployeeCreationDto;
import br.com.v1.barber.dto.employeeDto.EmployeeDto;
import br.com.v1.barber.dto.employeeDto.EmployeeUpdateDto;
import br.com.v1.barber.dto.servicesDto.ServicesCreationDto;
import br.com.v1.barber.dto.servicesDto.ServicesDto;
import br.com.v1.barber.dto.servicesDto.ServicesUpdateDto;

import java.util.List;

public interface EmployeeService {

    public Employee findEmployeeById (String id);
    public List<EmployeeDto> getAllEmployee();
    public EmployeeDto createEmployee(EmployeeCreationDto employeeCreationDto);
    public void deleteEmployee (String id);
    public EmployeeDto updateEmployee (String id, EmployeeUpdateDto employeeUpdateDto);
}
