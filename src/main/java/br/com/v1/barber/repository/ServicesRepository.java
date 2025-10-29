package br.com.v1.barber.repository;

import br.com.v1.barber.domain.Services;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServicesRepository extends MongoRepository<Services,String> {
}
