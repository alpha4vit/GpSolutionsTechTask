package by.gurinovich.cryptologos.gpsolutionstechtask.service;

import by.gurinovich.cryptologos.gpsolutionstechtask.dto.HotelFilterDTO;
import by.gurinovich.cryptologos.gpsolutionstechtask.entity.*;
import by.gurinovich.cryptologos.gpsolutionstechtask.repository.HotelRepository;
import by.gurinovich.cryptologos.gpsolutionstechtask.service.impl.HotelServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class HotelServiceTest {

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private AmenityService amenityService;

    @Mock
    private AddressService addressService;

    @Mock
    private ArrivalTimeService arrivalTimeService;

    @Mock
    private BrandService brandService;

    @InjectMocks
    private HotelServiceImpl hotelService;

    private Amenity amenity;
    private Hotel hotel;
    private HotelFilterDTO hotelFilterDTO;
    private Brand brand;
    private Address address;
    private ArrivalTime arrivalTime;

    @BeforeEach
    void setUp(){
        this.amenity = Amenity.builder()
                .id(1L)
                .name("Hilton")
                .hotels(new ArrayList<>())
                .build();
        this.brand = Brand.builder()
                .name("BMW")
                .build();
        this.arrivalTime = ArrivalTime.builder()
                .id(1L)
                .start(LocalTime.MIN)
                .end(LocalTime.MAX)
                .build();
        this.address = Address.builder()
                .id(1L)
                .city("Minsk")
                .build();
        this.hotel = Hotel.builder()
                .id(1L)
                .name("Hilton")
                .brand(brand)
                .arrivalTime(arrivalTime)
                .address(address)
                .build();
        this.hotelFilterDTO = HotelFilterDTO.builder()
                .name("Minsk")
                .brand("Hilton")
                .country("Belarus")
                .build();
    }

    @Test
    void getAll() {
        Mockito.when(hotelRepository.findAll())
                .thenReturn(List.of(hotel));
        final var res = hotelService.getAll();
        Assertions.assertTrue(res.contains(hotel));
        Mockito.verify(hotelRepository, Mockito.times(1))
                .findAll();
    }

    @Test
    void getAllWithFilter() {
        Mockito.when(hotelRepository.findAll(Mockito.any(Specification.class)))
                .thenReturn(List.of(hotel));
        final var res = hotelService.getAll(hotelFilterDTO);
        Assertions.assertTrue(res.contains(hotel));
        Mockito.verify(hotelRepository, Mockito.times(1))
                .findAll(Mockito.any(Specification.class));
    }

    @Test
    void getByIdSuccessful() {
        Mockito.when(hotelRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(hotel));
        final var res = hotelService.getById(Mockito.anyLong());
        Assertions.assertEquals(res, hotel);
        Mockito.verify(hotelRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    void throwNotFoundExceptionWhenHotelNotFoundGetById() {
        Mockito.when(hotelRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> hotelService.getById(Mockito.anyLong()));
        Mockito.verify(hotelRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    void saveSuccessful() {
        Mockito.when(brandService.getOrSave(Mockito.any(Brand.class)))
                .thenReturn(brand);
        Mockito.when(addressService.getOrSave(Mockito.any(Address.class)))
                        .thenReturn(address);
        Mockito.when(arrivalTimeService.getOrSave(Mockito.any(ArrivalTime.class)))
                .thenReturn(arrivalTime);
        Mockito.when(hotelRepository.save(Mockito.any(Hotel.class))).thenReturn(hotel);
        final var res = hotelService.save(hotel);
        Assertions.assertEquals(res, hotel);
        Mockito.verify(brandService, Mockito.times(1))
                .getOrSave(Mockito.any(Brand.class));
        Mockito.verify(addressService, Mockito.times(1))
                .getOrSave(Mockito.any(Address.class));
        Mockito.verify(arrivalTimeService, Mockito.times(1))
                .getOrSave(Mockito.any(ArrivalTime.class));
        Mockito.verify(hotelRepository, Mockito.times(1))
                .save(Mockito.any(Hotel.class));
    }

    @Test
    void addExistedAmenitySuccessful(){
        var amenities = new ArrayList<Amenity>();
        amenities.add(amenity);
        hotel.setAmenities(amenities);
        Mockito.when(hotelRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(hotel));
        final var res = hotelService.addAmenities(1L, List.of(amenity));
        Assertions.assertEquals(res, hotel);
        Assertions.assertEquals(res.getAmenities().size(), hotel.getAmenities().size());
        Mockito.verify(amenityService, Mockito.times(0))
                .getOrSave(Mockito.any(Amenity.class), Mockito.any(Hotel.class));

    }

    @Test
    void addCreatedAmenitySuccessful(){
        var amenities = new ArrayList<Amenity>();
        hotel.setAmenities(amenities);
        Mockito.when(hotelRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(hotel));
        Mockito.when(amenityService.getOrSave(Mockito.any(Amenity.class), Mockito.any(Hotel.class)))
                .thenReturn(amenity);
        final var res = hotelService.addAmenities(1L, List.of(amenity));
        Assertions.assertEquals(res, hotel);
        Assertions.assertTrue(res.getAmenities().contains(amenity));
        Mockito.verify(amenityService, Mockito.times(1))
                .getOrSave(Mockito.any(Amenity.class), Mockito.any(Hotel.class));

    }

}
