package br.com.v1.barber.dto.mapping;

import br.com.v1.barber.domain.Appointment;
import br.com.v1.barber.dto.appointmentDto.AppointmentCreationDto;
import br.com.v1.barber.dto.appointmentDto.AppointmentDto;
import br.com.v1.barber.dto.appointmentDto.AppointmentUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AppointmentMapper {

    Appointment appointmentCreationDtoToAppointment (AppointmentCreationDto appointmentCreationDto);

    AppointmentDto appointmentToAppointmentDto (Appointment appointment);

    List<AppointmentDto> appointmentListToAppointmentListDto (List<Appointment> appointmentList);

    void appointmentUpdateDtoToAppointment(AppointmentUpdateDto appointmentUpdateDto, @MappingTarget Appointment appointment);
}
