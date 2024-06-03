package by.gurinovich.cryptologos.gpsolutionstechtask.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Contacts {

    private String phone;

    private String email;

}
