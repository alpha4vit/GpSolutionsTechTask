package by.gurinovich.cryptologos.gpsolutionstechtask.service;

import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Amenity;
import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Hotel;

import java.util.List;

public interface HotelService {

    List<Hotel> getAll();

    Hotel getById(Long id);

    Hotel save(Hotel hotel);

    Hotel addAmenities(Long id, List<Amenity> amenities);

}

