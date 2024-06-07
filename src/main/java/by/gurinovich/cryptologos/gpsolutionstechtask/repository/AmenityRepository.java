package by.gurinovich.cryptologos.gpsolutionstechtask.repository;

import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Long> {
    Optional<Amenity> findByName(String name);

    @Query(value = "select a.name, count(ha.hotel_id) from amenities a join hotel_amenity ha on ha.amenity_id = a.id group by ha.hotel_id, a.name", nativeQuery = true)
    List<Object[]> groupAmenityByParam(@Param("param") String param);

}
