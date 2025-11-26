package br.com.v1.barber.dto.employeeDto;

import br.com.v1.barber.domain.WorkSchedule;
import br.com.v1.barber.dto.userDto.UserUpdateDto;
import br.com.v1.barber.enumerator.WeekDay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor // gera construtor vazio
@AllArgsConstructor
public class EmployeeUpdateDto extends UserUpdateDto {


    private List<WorkSchedule> workSchedules;

}
