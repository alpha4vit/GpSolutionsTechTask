package by.gurinovich.cryptologos.gpsolutionstechtask.repository;

import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Long> {
    Optional<Amenity> findByName(String name);
}
