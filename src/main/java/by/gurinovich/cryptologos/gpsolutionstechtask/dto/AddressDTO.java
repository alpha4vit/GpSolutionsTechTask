package by.gurinovich.cryptologos.gpsolutionstechtask.dto;

import jakarta.validation.constraints.NotBlank;

public record AddressDTO(
        @NotBlank(message = "House number cant be blank!")
        String houseNumber,

        @NotBlank(message = "Street cant be blank!")
        String street,

        @NotBlank(message = "City cant be blank!")
        String city,

        @NotBlank(message = "Country cant be blank!")
        String country,

        @NotBlank(message = "Postcode cant be blank!")
        String postCode
) {}
