package by.gurinovich.cryptologos.gpsolutionstechtask.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;

    private String city;

    private String street;

    private String houseNumber;

    private String postCode;

    @OneToMany(mappedBy = "address")
    private List<Hotel> hotels;
}
