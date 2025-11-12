package br.com.v1.barber.dto.employeeDto;

import br.com.v1.barber.domain.WorkSchedule;
import br.com.v1.barber.dto.userDto.UserCreationDto;
import br.com.v1.barber.enumerator.WeekDay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCreationDto extends UserCreationDto {

    private List<WorkSchedule> workSchedules;


    public void initSchedules() {
        if (workSchedules == null) {
            workSchedules = new ArrayList<>();
        }
        for (WeekDay day : WeekDay.values()) {
            workSchedules.add(new WorkSchedule(day, null, null));
        }
    }
}
