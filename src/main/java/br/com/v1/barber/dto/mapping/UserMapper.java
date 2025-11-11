package br.com.v1.barber.dto.mapping;


import br.com.v1.barber.domain.Client;
import br.com.v1.barber.domain.User;
import br.com.v1.barber.dto.clientDto.ClientCreationDto;
import br.com.v1.barber.dto.clientDto.ClientDto;
import br.com.v1.barber.dto.clientDto.ClientUpdateDto;
import br.com.v1.barber.dto.userDto.UserCreationDto;
import br.com.v1.barber.dto.userDto.UserDto;
import br.com.v1.barber.dto.userDto.UserUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper {

    Client userCreationDtoToUser(UserCreationDto userCreationDto);

    UserDto userToUserDto (User user);

    List<UserDto> userListToUserListDto (List <User> users);

    void userUpdateDtoToUser(UserUpdateDto userUpdateDto, @MappingTarget User user);
}
