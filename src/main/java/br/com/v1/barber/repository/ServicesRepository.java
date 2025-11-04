package br.com.v1.barber.repository;

import br.com.v1.barber.domain.Client;
import br.com.v1.barber.domain.Services;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ServicesRepository extends MongoRepository<Services,String> {

    Optional<Services> findTopByNameEqualsIgnoreCase(String name);
}
