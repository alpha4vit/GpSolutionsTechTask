package by.gurinovich.cryptologos.gpsolutionstechtask.util.mapper;

import by.gurinovich.cryptologos.gpsolutionstechtask.config.MapstructConfig;
import by.gurinovich.cryptologos.gpsolutionstechtask.dto.AddressDTO;
import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Address;
import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapstructConfig.class)
public interface AddressMapper {

    @Mapping(target = "country", source = "country.name")
    AddressDTO toDTO(Address address);

    Address toEntity(AddressDTO addressDTO);

    default Country stringToCountry(String name){
        return Country.builder().name(name).build();
    }
}
