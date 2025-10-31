package br.com.v1.barber.repository;

import br.com.v1.barber.domain.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository< Employee, String> {
}
