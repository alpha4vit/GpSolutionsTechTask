package by.gurinovich.cryptologos.gpsolutionstechtask.service.impl;

import by.gurinovich.cryptologos.gpsolutionstechtask.repository.AddressRepository;
import by.gurinovich.cryptologos.gpsolutionstechtask.repository.AmenityRepository;
import by.gurinovich.cryptologos.gpsolutionstechtask.repository.BrandRepository;
import by.gurinovich.cryptologos.gpsolutionstechtask.service.HistogramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Service
@RequiredArgsConstructor
public class HistogramServiceImpl implements HistogramService {

    private final AmenityRepository amenityRepository;
    private final BrandRepository brandRepository;
    private final AddressRepository addressRepository;

    @Override
    public Map<String, String> handleRequest(String param) {
        return switch (param) {
            case "city" -> getCityHistogram(param);
            case "country" -> getCountryHistogram(param);
            case "brand" -> getBrandHistogram(param);
            case "amenities" -> getAmenityHistogram(param);
            default -> throw new IllegalArgumentException("This param is not available for histogram!");
        };
    }

    @Override
    public Map<String, String> getCityHistogram(String city) {
        var objects = addressRepository.groupCities();
        return mapObjects(objects);
    }

    @Override
    public Map<String, String> getBrandHistogram(String brand) {
        var objects = brandRepository.groupBrands();
        return mapObjects(objects);
    }

    @Override
    public Map<String, String> getCountryHistogram(String country) {
        var objects = addressRepository.groupCountries();
        return mapObjects(objects);
    }

    @Override
    public Map<String, String> getAmenityHistogram(String amenity) {
        var objects = amenityRepository.groupAmenityByParam();
        return mapObjects(objects);
    }

    private Map<String, String> mapObjects(List<Object[]> objects){
        record Entry(String key, String value){}
        return objects.stream()
                .map(object -> new Entry(object[0].toString(), object[1].toString()))
                .collect(toMap(Entry::key, Entry::value));
    }
}
