package by.gurinovich.cryptologos.gpsolutionstechtask.repository;

import by.gurinovich.cryptologos.gpsolutionstechtask.entity.ArrivalTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface ArrivalTimeRepository extends JpaRepository<ArrivalTime, Long> {
    Optional<ArrivalTime> findByStartAndEnd(LocalTime start, LocalTime end);
}
