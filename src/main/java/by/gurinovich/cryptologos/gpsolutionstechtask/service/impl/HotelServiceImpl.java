package by.gurinovich.cryptologos.gpsolutionstechtask.service.impl;

import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Hotel;
import by.gurinovich.cryptologos.gpsolutionstechtask.repository.HotelRepository;
import by.gurinovich.cryptologos.gpsolutionstechtask.service.AddressService;
import by.gurinovich.cryptologos.gpsolutionstechtask.service.ArrivalTimeService;
import by.gurinovich.cryptologos.gpsolutionstechtask.service.BrandService;
import by.gurinovich.cryptologos.gpsolutionstechtask.service.HotelService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final ArrivalTimeService arrivalTimeService;
    private final BrandService brandService;
    private final AddressService addressService;

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
        hotel.setArrivalTime(arrivalTimeService.getOrSave(hotel.getArrivalTime()));
        hotel.setBrand(brandService.getOrSave(hotel.getBrand()));
        hotel.setAddress(addressService.getOrSave(hotel.getAddress()));
        return hotelRepository.save(hotel);
    }
}
