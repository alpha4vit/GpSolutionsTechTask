package by.gurinovich.cryptologos.gpsolutionstechtask.dto;


import lombok.Builder;

import java.util.Map;

@Builder
public record HotelFilterDTO(
    String name,
    String brand,
    String city,
    String country,
    String amenities
) {

    public static HotelFilterDTO of(Map<String, String> map){
        var builder = HotelFilterDTO.builder();
        map.forEach((key, val) -> {
            switch (key){
                case "name" -> builder.name(val);
                case "city" -> builder.city(val);
                case "country" -> builder.country(val);
                case "amenities" -> builder.amenities(val);
                case "brand" -> builder.brand(val);
            }
        });
        return builder.build();
    }

}
