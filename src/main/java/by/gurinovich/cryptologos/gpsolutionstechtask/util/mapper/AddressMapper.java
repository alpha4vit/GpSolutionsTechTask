package by.gurinovich.cryptologos.gpsolutionstechtask.util.mapper;

import by.gurinovich.cryptologos.gpsolutionstechtask.config.MapstructConfig;
import by.gurinovich.cryptologos.gpsolutionstechtask.dto.AddressDTO;
import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Address;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapstructConfig.class)
public interface AddressMapper {

    @Mapping(target = "country", source = "address.country.name")
    AddressDTO toDTO(Address address);

    @InheritInverseConfiguration
    Address toEntity(AddressDTO addressDTO);

}
