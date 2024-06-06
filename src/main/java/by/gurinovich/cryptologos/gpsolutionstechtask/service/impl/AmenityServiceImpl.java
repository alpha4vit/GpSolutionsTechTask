package by.gurinovich.cryptologos.gpsolutionstechtask.service.impl;

import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Amenity;
import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Hotel;
import by.gurinovich.cryptologos.gpsolutionstechtask.repository.AmenityRepository;
import by.gurinovich.cryptologos.gpsolutionstechtask.service.AmenityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AmenityServiceImpl implements AmenityService {

    private final AmenityRepository amenityRepository;

    @Override
    public Amenity getOrSave(Amenity amenity) {
        var check = amenityRepository.findByName(amenity.getName());
        return check.orElseGet(() -> amenityRepository.save(amenity));
    }

    @Override
    @Transactional
    public Amenity save(Amenity amenity, Hotel hotel) {
        var check = amenityRepository.findByName(amenity.getName());
        if (check.isPresent()){
            var existed = check.get();
            existed.getHotels().add(hotel);
            return existed;
        }
        amenity.setHotels(List.of(hotel));
        return amenity;
    }
}
