package by.gurinovich.cryptologos.gpsolutionstechtask.service;

import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Amenity;
import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Hotel;
import by.gurinovich.cryptologos.gpsolutionstechtask.repository.AmenityRepository;
import by.gurinovich.cryptologos.gpsolutionstechtask.service.impl.AmenityServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AmenityServiceTest {

    @Mock
    private AmenityRepository amenityRepository;

    @InjectMocks
    private AmenityServiceImpl amenityService;

    private Amenity amenity;
    private Hotel hotel;

    @BeforeEach
    void setUp(){
        this.amenity = Amenity.builder()
                .id(1L)
                .name("Hilton")
                .hotels(new ArrayList<>())
                .build();
        this.hotel = Hotel.builder()
                .id(1L)
                .name("Hilton")
                .amenities(List.of(amenity))
                .build();
        this.amenity.getHotels().add(hotel);
    }

    @Test
    void getExistedObjectWhenGerOrGetOrSave() {
        Mockito.when(amenityRepository.findByNameIgnoreCase(Mockito.anyString()))
                .thenReturn(Optional.of(amenity));
        final var res = amenityService.getOrSave(amenity, hotel);
        Assertions.assertEquals(amenity, res);
        Assertions.assertEquals(amenity.getName(), res.getName());
        Assertions.assertEquals(amenity.getId(), res.getId());
        Assertions.assertTrue(amenity.getHotels().contains(hotel));
        Assertions.assertTrue(hotel.getAmenities().contains(amenity));
        Mockito.verify(amenityRepository, Mockito.times(0))
                .save(Mockito.any(Amenity.class));
        Mockito.verify(amenityRepository, Mockito.times(1))
                .findByNameIgnoreCase(Mockito.anyString());
    }

    @Test
    void saveObjectWhenGerOrGetOrSave() {
        Mockito.when(amenityRepository.findByNameIgnoreCase(Mockito.anyString()))
                .thenReturn(Optional.empty());
        Mockito.when(amenityRepository.save(Mockito.any(Amenity.class)))
                .thenReturn(amenity);
        final var res = amenityService.getOrSave(amenity, hotel);
        Assertions.assertEquals(amenity, res);
        Assertions.assertEquals(amenity, res);
        Assertions.assertEquals(amenity.getName(), res.getName());
        Assertions.assertEquals(amenity.getId(), res.getId());
        Assertions.assertTrue(amenity.getHotels().contains(hotel));
        Assertions.assertTrue(hotel.getAmenities().contains(amenity));
        Mockito.verify(amenityRepository, Mockito.times(1))
                .save(amenity);
        Mockito.verify(amenityRepository, Mockito.times(1))
                .findByNameIgnoreCase(Mockito.anyString());
    }
    
}
