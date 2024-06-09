package by.gurinovich.cryptologos.gpsolutionstechtask.service;

import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Country;
import by.gurinovich.cryptologos.gpsolutionstechtask.repository.CountryRepository;
import by.gurinovich.cryptologos.gpsolutionstechtask.service.impl.CountryServiceImpl;
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
class CountryServiceTest {

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryServiceImpl countryService;

    private Country country;

    @BeforeEach
    void setUp(){
        this.country = Country.builder()
                .id(1L)
                .name("roma")
                .build();
    }

    @Test
    void getExistedObjectWhenGerOrSave() {
        Mockito.when(countryRepository.findByNameIgnoreCase(Mockito.anyString()))
                .thenReturn(Optional.of(country));
        final var res = countryService.getOrSave(country);
        Assertions.assertEquals(country, res);
        Assertions.assertEquals(country.getId(), res.getId());
        Assertions.assertEquals(country.getName(), res.getName());
        Mockito.verify(countryRepository, Mockito.times(0))
                .save(Mockito.any(Country.class));
        Mockito.verify(countryRepository, Mockito.times(1))
                .findByNameIgnoreCase(Mockito.anyString());
    }

    @Test
    void saveObjectWhenGerOrSave() {
        Mockito.when(countryRepository.findByNameIgnoreCase(Mockito.anyString()))
                .thenReturn(Optional.empty());
        Mockito.when(countryRepository.save(Mockito.any(Country.class)))
                .thenReturn(country);
        final var res = countryService.getOrSave(country);
        Assertions.assertEquals(country, res);
        Assertions.assertEquals(country.getId(), res.getId());
        Assertions.assertEquals(country.getName(), res.getName());
        Mockito.verify(countryRepository, Mockito.times(1))
                .save(country);
        Mockito.verify(countryRepository, Mockito.times(1))
                .findByNameIgnoreCase(Mockito.anyString());
    }
}
