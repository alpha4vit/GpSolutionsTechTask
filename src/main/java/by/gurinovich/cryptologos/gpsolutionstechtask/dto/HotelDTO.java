package by.gurinovich.cryptologos.gpsolutionstechtask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record HotelDTO(
        Long id,

        @NotBlank(message = "Name cant be blank!")
        String name,

        String description,

        @NotBlank(message = "Brand cant be blank!")
        String brand,

        @Valid
        AddressDTO address,

        @Valid
        ContactsDTO contacts,

        @Valid
        ArrivalTimeDTO arrivalTime,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        List<String> amenities
) {
}
