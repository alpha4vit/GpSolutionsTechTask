package by.gurinovich.cryptologos.gpsolutionstechtask.service;

import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Address;
import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Country;
import by.gurinovich.cryptologos.gpsolutionstechtask.repository.AddressRepository;
import by.gurinovich.cryptologos.gpsolutionstechtask.service.impl.AddressServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private CountryService countryService;

    @InjectMocks
    private AddressServiceImpl addressService;

    private Country country;
    private Address address;

    @BeforeEach
    void setUp(){
        country = Country.builder()
                .id(1L)
                .name("Belarus")
                .build();
        address = Address.builder()
                .id(1L)
                .city("Minsk")
                .country(country)
                .houseNumber("9")
                .street("Pobediteley Avenue")
                .postCode("220004")
                .build();
    }

    @Test
    void returnExistedAddress(){
        Mockito.when(addressRepository.findByHouseNumberIgnoreCaseAndStreetIgnoreCaseAndCityIgnoreCaseAndCountryAndPostCodeIgnoreCase(Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.any(Country.class),
                Mockito.anyString()))
                .thenReturn(Optional.of(address));
        Mockito.when(countryService.getOrSave(Mockito.any(Country.class)))
                .thenReturn(country);
        final var result = addressService.getOrSave(address);
        Assertions.assertEquals(result, address);
        Mockito.verify(addressRepository, Mockito.times(1)).findByHouseNumberIgnoreCaseAndStreetIgnoreCaseAndCityIgnoreCaseAndCountryAndPostCodeIgnoreCase(Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.any(Country.class),
                Mockito.anyString());
        Mockito.verify(addressRepository, Mockito.times(0))
                .save(Mockito.any(Address.class));
        Mockito.verify(countryService, Mockito.times(1))
                .getOrSave(Mockito.any(Country.class));
    }

    @Test
    void saveNewAddress(){
        Mockito.when(addressRepository.findByHouseNumberIgnoreCaseAndStreetIgnoreCaseAndCityIgnoreCaseAndCountryAndPostCodeIgnoreCase(Mockito.anyString(),
                        Mockito.anyString(),
                        Mockito.anyString(),
                        Mockito.any(Country.class),
                        Mockito.anyString()))
                .thenReturn(Optional.empty());
        Mockito.when(countryService.getOrSave(Mockito.any(Country.class)))
                .thenReturn(country);
        Mockito.when(addressRepository.save(Mockito.any(Address.class)))
                .thenReturn(address);
        final var result = addressService.getOrSave(address);
        Assertions.assertEquals(result, address);
        Mockito.verify(addressRepository, Mockito.times(1)).findByHouseNumberIgnoreCaseAndStreetIgnoreCaseAndCityIgnoreCaseAndCountryAndPostCodeIgnoreCase(Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.any(Country.class),
                Mockito.anyString());
        Mockito.verify(addressRepository, Mockito.times(1))
                .save(Mockito.any(Address.class));
        Mockito.verify(countryService, Mockito.times(1))
                .getOrSave(Mockito.any(Country.class));
    }

}
