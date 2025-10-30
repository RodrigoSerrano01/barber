package br.com.v1.barber.service;

import br.com.v1.barber.domain.Client;
import br.com.v1.barber.domain.Services;
import br.com.v1.barber.dto.clientDto.ClientCreationDto;
import br.com.v1.barber.dto.clientDto.ClientDto;
import br.com.v1.barber.dto.clientDto.ClientUpdateDto;
import br.com.v1.barber.dto.servicesDto.ServicesCreationDto;
import br.com.v1.barber.dto.servicesDto.ServicesDto;
import br.com.v1.barber.dto.servicesDto.ServicesUpdateDto;
import br.com.v1.barber.repository.ClientRepository;
import br.com.v1.barber.repository.ServicesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServicesService {


    public Services findServiceById (String id);
    public List<ServicesDto> getAllServices();
    public ServicesDto createServices(ServicesCreationDto servicesCreationDto);
    public void deleteServices (String id);
    public ServicesDto updateServices (String id, ServicesUpdateDto servicesUpdateDto);
}
