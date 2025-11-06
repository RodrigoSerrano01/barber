package br.com.v1.barber.domain;

import br.com.v1.barber.enumerator.WeekDay;
import br.com.v1.barber.util.ScheduleUtils;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class WorkSchedule {

    private WeekDay weekDay;
    private LocalTime startTime;
    private LocalTime endTime;
    private Boolean working;
    private List<TimeSlot> slots;

    public WorkSchedule(WeekDay weekDay, LocalTime startTime, LocalTime endTime) {
        this.weekDay = weekDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.working = false;
    }
}
