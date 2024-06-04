package by.gurinovich.cryptologos.gpsolutionstechtask.util.mapper;

import by.gurinovich.cryptologos.gpsolutionstechtask.config.MapstructConfig;
import by.gurinovich.cryptologos.gpsolutionstechtask.dto.ArrivalTimeDTO;
import by.gurinovich.cryptologos.gpsolutionstechtask.entity.ArrivalTime;
import org.mapstruct.Mapper;

@Mapper(config = MapstructConfig.class)
public interface ArrivalTimeMapper {

    ArrivalTime toEntity(ArrivalTimeDTO arrivalTimeDTO);

    ArrivalTimeDTO toDTO(ArrivalTime arrivalTime);

}
