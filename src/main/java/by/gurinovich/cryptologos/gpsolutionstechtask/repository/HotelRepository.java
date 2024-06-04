package by.gurinovich.cryptologos.gpsolutionstechtask.repository;

import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
