package br.com.v1.barber.dto.mapping;


import br.com.v1.barber.domain.Client;
import br.com.v1.barber.domain.Employee;
import br.com.v1.barber.dto.clientDto.ClientCreationDto;
import br.com.v1.barber.dto.clientDto.ClientDto;
import br.com.v1.barber.dto.clientDto.ClientUpdateDto;
import br.com.v1.barber.dto.employeeDto.EmployeeCreationDto;
import br.com.v1.barber.dto.employeeDto.EmployeeDto;
import br.com.v1.barber.dto.employeeDto.EmployeeUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface EmployeeMapper {

    Employee employeeCreationDtoToEmployee(EmployeeCreationDto employeeCreationDto);

    EmployeeDto employeeToEmployeeDto (Employee employee);

    List<EmployeeDto> employeeListToEmployeeListDto (List <Employee> employees);

    void employeeUpdateDtoToEmployee (EmployeeUpdateDto employeeUpdateDto, @MappingTarget Employee employee);
}
