package by.gurinovich.cryptologos.gpsolutionstechtask.service.impl;

import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Hotel;
import by.gurinovich.cryptologos.gpsolutionstechtask.repository.HotelRepository;
import by.gurinovich.cryptologos.gpsolutionstechtask.service.HotelService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;


    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hotel with this id not found!"));
    }

    @Override
    public Hotel save(Hotel hotel) {
        return hotelRepository.save(hotel); //TODO
    }
}
