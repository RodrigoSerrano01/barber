package br.com.v1.barber.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data

public class TimeSlot {

        private LocalTime time;
        private boolean available;

        // construtores, getters, setters

    public TimeSlot(LocalTime time, boolean available) {
        this.time = time;
        this.available = available;
    }
}
