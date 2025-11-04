package br.com.v1.barber.service.impl;

import br.com.v1.barber.domain.Client;
import br.com.v1.barber.domain.Services;
import br.com.v1.barber.dto.mapping.ServicesMapper;
import br.com.v1.barber.dto.servicesDto.ServicesCreationDto;
import br.com.v1.barber.dto.servicesDto.ServicesDto;
import br.com.v1.barber.dto.servicesDto.ServicesUpdateDto;
import br.com.v1.barber.exception.handler.ClientAlreadyExistsException;
import br.com.v1.barber.exception.handler.ServicesAlreadyExistsException;
import br.com.v1.barber.exception.handler.ServicesNotFoundException;
import br.com.v1.barber.exception.handler.UserNotFoundException;
import br.com.v1.barber.repository.ServicesRepository;
import br.com.v1.barber.service.ServicesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static br.com.v1.barber.enumerator.Error.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServicesServiceImpl implements ServicesService {

    private final ServicesRepository repository;
    private final ServicesMapper servicesMapper;

    @Override
    public Services findServiceById(String id) {
        log.info("Searching service by id {}",id);
        return repository.findById(id)
                .orElseThrow(() -> new ServicesNotFoundException(NO_SERVICE_FOUND_BY_ID.getErrorDescription()));
    }

    @Override
    public List<ServicesDto> getAllServices() {
        log.info("Searching for services");
        final List<Services> services = repository.findAll();
        if(services.isEmpty()){
            log.info((NO_SERVICE_FOUND.getErrorDescription()));
            throw new UserNotFoundException(NO_SERVICE_FOUND.getErrorDescription());
        }
        return servicesMapper.servicesListToServicesListDto(services);
    }

    @Override
    public ServicesDto createServices(ServicesCreationDto servicesCreationDto) {
        log.info("Searching Service by name",servicesCreationDto.getName());
        final Optional<Services> serviceSearching = repository.findTopByNameEqualsIgnoreCase(servicesCreationDto.getName());
        if(serviceSearching.isPresent()){
            log.info(SERVICE_ALREADY_REGISTERED.getErrorDescription());
            throw new ServicesAlreadyExistsException(SERVICE_ALREADY_REGISTERED.getErrorDescription());
        }
        final Services services = servicesMapper.servicesCreationDtoToServices(servicesCreationDto);
       repository.save(services);
        log.info("Sucess registered{} ",services.getName());
       return servicesMapper.servicesToServicesDto(services);
    }

    @Override
    public void deleteServices(String id) {
        final Services services = this.findServiceById(id);
        repository.deleteById(id);
        log.info("Client {} deleted",services.getName());
    }

    @Override
    public ServicesDto updateServices(String id, ServicesUpdateDto servicesUpdateDto) {
        final Services existingService = this.findServiceById(id);
        servicesMapper.servicesUpdateDtoToServices(servicesUpdateDto, existingService);
        repository.save(existingService);
        log.info("Sucess registered{} ",existingService.getName());
        return servicesMapper.servicesToServicesDto(existingService);
    }

}
