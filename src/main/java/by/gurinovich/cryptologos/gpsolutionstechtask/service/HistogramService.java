package by.gurinovich.cryptologos.gpsolutionstechtask.service;

import java.util.Map;

public interface HistogramService {

    Map<String, String> handleRequest(String param);

    Map<String, String> getCityHistogram(String city);

    Map<String, String> getBrandHistogram(String brand);

    Map<String, String> getCountryHistogram(String country);

    Map<String, String> getAmenityHistogram(String amenity);
}
