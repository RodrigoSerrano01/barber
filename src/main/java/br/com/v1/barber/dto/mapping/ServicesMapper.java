package br.com.v1.barber.dto.mapping;


import br.com.v1.barber.domain.Client;
import br.com.v1.barber.domain.Services;
import br.com.v1.barber.dto.clientDto.ClientCreationDto;
import br.com.v1.barber.dto.clientDto.ClientDto;
import br.com.v1.barber.dto.clientDto.ClientUpdateDto;
import br.com.v1.barber.dto.servicesDto.ServicesCreationDto;
import br.com.v1.barber.dto.servicesDto.ServicesDto;
import br.com.v1.barber.dto.servicesDto.ServicesUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ServicesMapper {

    Services servicesCreationDtoToServices(ServicesCreationDto servicesCreationDto);

    ServicesDto servicesToServicesDto (Services services);

    List<ServicesDto> servicesListToServicesListDto (List <Services> services);

    void servicesUpdateDtoToServices (ServicesUpdateDto servicesUpdateDto, @MappingTarget Services services);
}
