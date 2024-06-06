package by.gurinovich.cryptologos.gpsolutionstechtask.dto;

import java.util.List;

public record HotelDTO(
        Long id,
        String name,
        String description,
        String brand,
        AddressDTO address,
        ContactsDTO contacts,
        ArrivalTimeDTO arrivalTime,
        List<String> amenities
) {
}
