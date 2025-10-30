package br.com.v1.barber.service.impl;

import br.com.v1.barber.domain.Services;
import br.com.v1.barber.dto.mapping.ServicesMapper;
import br.com.v1.barber.dto.servicesDto.ServicesCreationDto;
import br.com.v1.barber.dto.servicesDto.ServicesDto;
import br.com.v1.barber.dto.servicesDto.ServicesUpdateDto;
import br.com.v1.barber.exception.handler.ServicesNotFoundException;
import br.com.v1.barber.exception.handler.UserNotFoundException;
import br.com.v1.barber.repository.ServicesRepository;
import br.com.v1.barber.service.ServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.v1.barber.enumerator.Error.NO_SERVICE_FOUND_BY_ID;

@Service
@RequiredArgsConstructor
public class ServicesServiceImpl implements ServicesService {

    private final ServicesRepository repository;
    private final ServicesMapper servicesMapper;

    @Override
    public Services findServiceById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ServicesNotFoundException(NO_SERVICE_FOUND_BY_ID.getErrorDescription()));
    }

    @Override
    public List<ServicesDto> getAllServices() {
        final List<Services> services = repository.findAll();
        return servicesMapper.servicesListToServicesListDto(services);
    }

    @Override
    public ServicesDto createServices(ServicesCreationDto servicesCreationDto) {
       final Services services = servicesMapper.servicesCreationDtoToServices(servicesCreationDto);
       repository.save(services);
       return servicesMapper.servicesToServicesDto(services);
    }

    @Override
    public void deleteServices(String id) {
        final Services services = this.findServiceById(id);
        repository.deleteById(id);
    }

    @Override
    public ServicesDto updateServices(String id, ServicesUpdateDto servicesUpdateDto) {
        final Services existingService = this.findServiceById(id);
        servicesMapper.servicesUpdateDtoToServices(servicesUpdateDto, existingService);
        repository.save(existingService);
        return servicesMapper.servicesToServicesDto(existingService);
    }

}
