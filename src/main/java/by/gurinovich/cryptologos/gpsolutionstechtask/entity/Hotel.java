package by.gurinovich.cryptologos.gpsolutionstechtask.entity;

import by.gurinovich.cryptologos.gpsolutionstechtask.model.Contacts;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brand;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Embedded
    private Contacts contacts;

    @ManyToOne
    @JoinColumn(name = "arrival_time_id", referencedColumnName = "id")
    private ArrivalTime arrivalTime;

    @ManyToMany
    private List<Amenity> amenities;

}
