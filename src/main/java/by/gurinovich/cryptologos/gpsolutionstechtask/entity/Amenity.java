package by.gurinovich.cryptologos.gpsolutionstechtask.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "amenities")
public class Amenity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EqualsAndHashCode.Include
    private String name;

    @ManyToMany
    @JoinTable(
            name = "hotel_amenity",
            joinColumns = @JoinColumn(name = "amenity_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    )
    private List<Hotel> hotels;

    public Amenity(String name) {
        this.name = name;
    }


}
