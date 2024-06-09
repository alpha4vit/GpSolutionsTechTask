package by.gurinovich.cryptologos.gpsolutionstechtask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ArrivalTimeDTO(

        @JsonProperty(value = "checkIn")
        @NotBlank(message = "Check in time cant be blank!")
        @Pattern(regexp = "^(?:[01]\\d|2[0-3]):[0-5]\\d$", message = "Invalid time format. Expected format is HH:mm")
        String start,

        @JsonProperty(value = "checkOut")
        @Pattern(regexp = "^(?:[01]\\d|2[0-3]):[0-5]\\d$", message = "Invalid time format. Expected format is HH:mm")
        String end
) {
}
