package br.com.v1.barber.repository;

import br.com.v1.barber.domain.Appointment;
import br.com.v1.barber.domain.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AppointmentRepository  extends MongoRepository<Appointment, String> {

    Optional<Appointment> findTopByIdEqualsIgnoreCase(String id);
}
