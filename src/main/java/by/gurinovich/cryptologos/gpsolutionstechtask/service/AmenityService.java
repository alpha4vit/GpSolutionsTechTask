package by.gurinovich.cryptologos.gpsolutionstechtask.service;

import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Amenity;
import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Hotel;

public interface AmenityService {

    Amenity getOrSave(Amenity amenity);

    Amenity save(Amenity amenity, Hotel hotel);

}
