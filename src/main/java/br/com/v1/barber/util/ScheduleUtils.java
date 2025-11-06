package br.com.v1.barber.util;

import br.com.v1.barber.domain.TimeSlot;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ScheduleUtils {

    public static List<TimeSlot> generateHalfHourSlots(LocalTime start, LocalTime end) {
        List<TimeSlot> slots = new ArrayList<>();
        LocalTime current = start;
        while (!current.isAfter(end.minusMinutes(30))) {
            slots.add(new TimeSlot(current,true));
            current = current.plusMinutes(30);
        }
        return slots;
    }
}
