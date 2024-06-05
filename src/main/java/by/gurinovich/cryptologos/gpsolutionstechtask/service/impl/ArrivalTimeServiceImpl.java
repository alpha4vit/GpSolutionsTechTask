package by.gurinovich.cryptologos.gpsolutionstechtask.service.impl;

import by.gurinovich.cryptologos.gpsolutionstechtask.entity.ArrivalTime;
import by.gurinovich.cryptologos.gpsolutionstechtask.repository.ArrivalTimeRepository;
import by.gurinovich.cryptologos.gpsolutionstechtask.service.ArrivalTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArrivalTimeServiceImpl implements ArrivalTimeService {

    private final ArrivalTimeRepository arrivalTimeRepository;

    @Override
    public ArrivalTime getOrSave(ArrivalTime arrivalTime) {
        var check = arrivalTimeRepository.findByStartAndEnd(arrivalTime.getStart(), arrivalTime.getEnd());
        return check.orElseGet(() -> arrivalTimeRepository.save(arrivalTime));
    }
}
