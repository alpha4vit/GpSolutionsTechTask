package by.gurinovich.cryptologos.gpsolutionstechtask.service.impl;

import by.gurinovich.cryptologos.gpsolutionstechtask.dto.HotelFilterDTO;
import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Amenity;
import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Hotel;
import by.gurinovich.cryptologos.gpsolutionstechtask.filter.HotelSpecification;
import by.gurinovich.cryptologos.gpsolutionstechtask.repository.HotelRepository;
import by.gurinovich.cryptologos.gpsolutionstechtask.service.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final ArrivalTimeService arrivalTimeService;
    private final BrandService brandService;
    private final AddressService addressService;
    private final AmenityService amenityService;

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public List<Hotel> getAll(HotelFilterDTO filter) {
        var spec = HotelSpecification.getFilterSpec(filter);
        return hotelRepository.findAll(spec);
    }

    @Override
    public Hotel getById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hotel with this id not found!"));
    }

    @Override
    @Transactional
    public Hotel save(Hotel hotel) {
        hotel.setArrivalTime(arrivalTimeService.getOrSave(hotel.getArrivalTime()));
        hotel.setBrand(brandService.getOrSave(hotel.getBrand()));
        hotel.setAddress(addressService.getOrSave(hotel.getAddress()));
        return hotelRepository.save(hotel);
    }

    @Override
    @Transactional
    public Hotel addAmenities(Long id, List<Amenity> amenities) {
        var hotel = getById(id);
        var saved = amenities.stream()
                .filter(amenity -> !hotel.getAmenities().contains(amenity))
                .peek(amenity -> amenityService.getOrSave(amenity, hotel))
                .toList();
        hotel.getAmenities().addAll(saved);
        return hotel;
    }
}
