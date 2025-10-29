package br.com.v1.barber.service;

import br.com.v1.barber.domain.Client;
import br.com.v1.barber.domain.Services;
import br.com.v1.barber.repository.ClientRepository;
import br.com.v1.barber.repository.ServicesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesService {

    private final ServicesRepository repository;

    public ServicesService(ServicesRepository repository) {
        this.repository = repository;
    }

    public List<Services> getAllServices(){
        return repository.findAll();
    }
    public Services createServices(Services services) {
        return repository.save(services);
    }

    public void deleteServices (String id){
        repository.deleteById(id);
    }
}
