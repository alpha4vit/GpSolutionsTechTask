package by.gurinovich.cryptologos.gpsolutionstechtask.util.mapper;

import by.gurinovich.cryptologos.gpsolutionstechtask.config.MapstructConfig;
import by.gurinovich.cryptologos.gpsolutionstechtask.dto.HotelDTO;
import by.gurinovich.cryptologos.gpsolutionstechtask.dto.HotelSummaryDTO;
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

    @Mapping(target = "address", expression = "java(hotel.getAddress().getShortAddress())")
    HotelSummaryDTO toSummaryDTO(Hotel hotel);

    List<HotelSummaryDTO> toSummaryDTOs(List<Hotel> hotels);

    @Mapping(target = "brand", source = "hotel.brand.name")
    @Mapping(target = "contacts.phone", source = "hotel.phone")
    @Mapping(target = "contacts.email", source = "hotel.email")
    @Mapping(target = "amenities", expression = "java(hotel.getAmenities().stream().map(Amenity::getName).toList())")
    HotelDTO toDTO(Hotel hotel);


    @InheritInverseConfiguration
    @Mapping(target = "amenities", expression = "java(hotelDTO.amenities().stream().map(Amenity::new).toList())")
    Hotel toEntity(HotelDTO hotelDTO);


}
