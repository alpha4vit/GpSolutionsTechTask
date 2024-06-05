package by.gurinovich.cryptologos.gpsolutionstechtask.util.mapper;

import by.gurinovich.cryptologos.gpsolutionstechtask.config.MapstructConfig;
import by.gurinovich.cryptologos.gpsolutionstechtask.dto.ArrivalTimeDTO;
import by.gurinovich.cryptologos.gpsolutionstechtask.entity.ArrivalTime;
import org.mapstruct.Mapper;

import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Mapper(config = MapstructConfig.class,
        imports = Time.class)
public interface ArrivalTimeMapper {

    ArrivalTimeDTO toDTO(ArrivalTime arrivalTime);

    ArrivalTime toEntity(ArrivalTimeDTO arrivalTimeDTO);

    default LocalTime mapStringToTime(String value) {
        return value != null ?
                LocalTime.parse(value) : null;
    }

    default String mapTimeToString(LocalTime value) {
        if (value == null)
            return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return value.format(formatter);
    }

}
