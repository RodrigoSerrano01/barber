package br.com.v1.barber.dto.mapping;


import br.com.v1.barber.domain.Client;
import br.com.v1.barber.dto.clientDto.ClientCreationDto;
import br.com.v1.barber.dto.clientDto.ClientDto;
import br.com.v1.barber.dto.clientDto.ClientUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ClientMapper {

    Client clientCreationDtoToClient(ClientCreationDto clientCreationDto);

    ClientDto clientToClientDto (Client client);

    List<ClientDto> clientListToClientListDto (List <Client> clients);

    void clientUpdateDtoToClient (ClientUpdateDto clientUpdateDto, @MappingTarget Client client);
}
