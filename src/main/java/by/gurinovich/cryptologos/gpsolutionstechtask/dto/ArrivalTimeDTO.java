package by.gurinovich.cryptologos.gpsolutionstechtask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ArrivalTimeDTO(

        @JsonProperty(value = "checkIn")
        String start,

        @JsonProperty(value = "checkOut")
        String end
) {
}
