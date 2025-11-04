package br.com.v1.barber.repository;

import br.com.v1.barber.domain.Employee;
import br.com.v1.barber.domain.Services;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmployeeRepository extends MongoRepository< Employee, String> {

    Optional<Employee> findTopByNameEqualsIgnoreCase(String name);
}
