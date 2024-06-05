package by.gurinovich.cryptologos.gpsolutionstechtask.util.mapper;

import by.gurinovich.cryptologos.gpsolutionstechtask.config.MapstructConfig;
import by.gurinovich.cryptologos.gpsolutionstechtask.dto.HotelDTO;
import by.gurinovich.cryptologos.gpsolutionstechtask.dto.HotelSummaryDTO;
import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Address;
import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Amenity;
import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Hotel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapstructConfig.class, 
        uses = {
            AddressMapper.class,
            ArrivalTimeMapper.class
        },
        imports = Amenity.class
)
public interface HotelMapper {


    HotelSummaryDTO toSummaryDTO(Hotel hotel);

    List<HotelSummaryDTO> toSummaryDTOs(List<Hotel> hotels);

    @Mapping(target = "brand", source = "hotel.brand.name")
    @Mapping(target = "contacts.phone", source = "hotel.phone")
    @Mapping(target = "contacts.email", source = "hotel.email")
    HotelDTO toDTO(Hotel hotel);


    @InheritInverseConfiguration
    Hotel toEntity(HotelDTO hotelDTO);

    default String getShortAddress(Address address){
        return address.getShortAddress();
    }

    default List<String> mapAmenitiesToString(List<Amenity> amenities){
        if (amenities == null)
            return null;
        return amenities.stream().map(Amenity::getName).toList();
    }

    default List<Amenity> mapStringsToAmenities(List<String> amenities){
        if (amenities == null)
            return null;
        return amenities.stream().map(Amenity::new).toList();
    }

}
