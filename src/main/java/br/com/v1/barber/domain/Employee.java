package br.com.v1.barber.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "employee")
public class Employee extends User {

    @Id
    private String id;

    private List<WorkSchedule> workSchedules;
}
