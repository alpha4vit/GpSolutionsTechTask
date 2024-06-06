package by.gurinovich.cryptologos.gpsolutionstechtask.util.mapper;

import by.gurinovich.cryptologos.gpsolutionstechtask.config.MapstructConfig;
import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Amenity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapstructConfig.class)
public interface AmenityMapper {

    List<String> toStringList(List<Amenity> amenities);

    List<Amenity> toEntities(List<String> amenities);


    default String mapToString(Amenity amenity){
        if (amenity == null)
            return null;
        return amenity.getName();
    }

    default Amenity mapToEntity(String amenity){
        if (amenity == null)
            return null;
        return new Amenity(amenity);
    }

}
