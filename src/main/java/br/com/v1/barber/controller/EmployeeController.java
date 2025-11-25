package br.com.v1.barber.controller;


import br.com.v1.barber.domain.Client;
import br.com.v1.barber.domain.Employee;
import br.com.v1.barber.dto.clientDto.ClientCreationDto;
import br.com.v1.barber.dto.clientDto.ClientDto;
import br.com.v1.barber.dto.clientDto.ClientUpdateDto;
import br.com.v1.barber.dto.employeeDto.EmployeeCreationDto;
import br.com.v1.barber.dto.employeeDto.EmployeeDto;
import br.com.v1.barber.dto.employeeDto.EmployeeUpdateDto;
import br.com.v1.barber.service.impl.EmployeeServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class EmployeeController extends RootController{
    private final EmployeeServiceImpl employeeServiceImpl;


    @GetMapping(path ="/employee")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDto> getAllClients(){
        return employeeServiceImpl.getAllEmployee();
    }

    @PostMapping(path ="/employee/add")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto createEmployee (@Valid @RequestBody EmployeeCreationDto employeeCreationDto){
        return employeeServiceImpl.createEmployee(employeeCreationDto);
    }

    @DeleteMapping(path ="/employee/{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public void deleteEmployee (@PathVariable String id){
        employeeServiceImpl.deleteEmployee(id);
    }

    @PutMapping("employee/{id}/update")
    public EmployeeDto updadteEmployee (@PathVariable String id,@Valid @RequestBody EmployeeUpdateDto employeeUpdateDto){
        return employeeServiceImpl.updateEmployee(id, employeeUpdateDto);
    }

    @PutMapping("employee/{id}/update-schedule")
    public EmployeeDto updadteEmployeeSchedule (@PathVariable String id,@Valid @RequestBody EmployeeUpdateDto employeeUpdateDto){
        return employeeServiceImpl.updateEmployee(id, employeeUpdateDto);
    }

    @GetMapping(path ="employee/{id}/find")
    @ResponseStatus(HttpStatus.OK)
    public Employee findEmployee (@PathVariable String id){
        return employeeServiceImpl.findEmployeeById(id);
    }

}
