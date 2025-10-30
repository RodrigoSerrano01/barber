package br.com.v1.barber.service;

import br.com.v1.barber.domain.Client;
import br.com.v1.barber.domain.Services;
import br.com.v1.barber.repository.ClientRepository;
import br.com.v1.barber.repository.ServicesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServicesService {


    public List<Services> getAllServices();
    public Services createServices(Services services);

    public void deleteServices (String id);
}
