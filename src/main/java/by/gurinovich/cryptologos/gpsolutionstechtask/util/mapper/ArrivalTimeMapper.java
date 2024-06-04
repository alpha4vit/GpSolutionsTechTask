package by.gurinovich.cryptologos.gpsolutionstechtask.util.mapper;

import by.gurinovich.cryptologos.gpsolutionstechtask.config.MapstructConfig;
import by.gurinovich.cryptologos.gpsolutionstechtask.dto.ArrivalTimeDTO;
import by.gurinovich.cryptologos.gpsolutionstechtask.entity.ArrivalTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.sql.Time;

@Mapper(config = MapstructConfig.class,
        imports = Time.class)
public interface ArrivalTimeMapper {

    @Mapping(target = "checkIn", expression = "java(arrivalTime.getCheckIn().toString())")
    @Mapping(target = "checkOut", expression = "java(arrivalTime.getCheckOut().toString())")
    ArrivalTimeDTO toDTO(ArrivalTime arrivalTime);

    @Mapping(target = "checkIn", expression = "java(Time.valueOf(arrivalTimeDTO.checkIn()))")
    @Mapping(target = "checkOut", expression = "java(Time.valueOf(arrivalTimeDTO.checkOut()))")
    ArrivalTime toEntity(ArrivalTimeDTO arrivalTimeDTO);

}
