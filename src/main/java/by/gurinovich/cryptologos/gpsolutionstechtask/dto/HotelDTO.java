package by.gurinovich.cryptologos.gpsolutionstechtask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record HotelDTO(
        Long id,

        String name,

        String description,

        String brand,

        AddressDTO address,

        ContactsDTO contacts,

        ArrivalTimeDTO arrivalTime,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        List<String> amenities
) {
}
