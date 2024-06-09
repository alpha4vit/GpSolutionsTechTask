package by.gurinovich.cryptologos.gpsolutionstechtask.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ContactsDTO(

        @NotBlank(message = "Phone cant be blank!")
        String phone,

        @NotBlank(message = "Email cant be blank!")
        @Email(message = "Invalid email format")
        String email

) {
}
