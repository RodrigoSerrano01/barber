package br.com.v1.barber.service.impl;

import br.com.v1.barber.domain.Services;
import br.com.v1.barber.repository.ServicesRepository;
import br.com.v1.barber.service.ServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ServicesServiceImpl implements ServicesService {

    private final ServicesRepository repository;
    @Override
    public List<Services> getAllServices(){
        return repository.findAll();
    }
    @Override
    public Services createServices(Services services) {
        return repository.save(services);
    }
    @Override
    public void deleteServices (String id){
        repository.deleteById(id);
    }
}
