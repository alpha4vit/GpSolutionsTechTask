package by.gurinovich.cryptologos.gpsolutionstechtask.service;

import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Brand;
import by.gurinovich.cryptologos.gpsolutionstechtask.repository.BrandRepository;
import by.gurinovich.cryptologos.gpsolutionstechtask.service.impl.BrandServiceImpl;
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
class BrandServiceTest {

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandServiceImpl brandService;

    private Brand brand;

    @BeforeEach
    void setUp(){
        this.brand = Brand.builder()
                .id(1L)
                .name("BMW")
                .build();
    }

    @Test
    void getExistedObjectWhenGerOrSave() {
        Mockito.when(brandRepository.findByNameIgnoreCase(Mockito.anyString()))
                .thenReturn(Optional.of(brand));
        final var res = brandService.getOrSave(brand);
        Assertions.assertEquals(brand, res);
        Assertions.assertEquals(brand.getId(), res.getId());
        Assertions.assertEquals(brand.getName(), res.getName());
        Mockito.verify(brandRepository, Mockito.times(0))
                .save(Mockito.any(Brand.class));
        Mockito.verify(brandRepository, Mockito.times(1))
                .findByNameIgnoreCase(Mockito.anyString());
    }

    @Test
    void saveObjectWhenGerOrSave() {
        Mockito.when(brandRepository.findByNameIgnoreCase(Mockito.anyString()))
                .thenReturn(Optional.empty());
        Mockito.when(brandRepository.save(Mockito.any(Brand.class)))
                .thenReturn(brand);
        final var res = brandService.getOrSave(brand);
        Assertions.assertEquals(brand, res);
        Assertions.assertEquals(brand.getId(), res.getId());
        Assertions.assertEquals(brand.getName(), res.getName());
        Mockito.verify(brandRepository, Mockito.times(1))
                .save(brand);
        Mockito.verify(brandRepository, Mockito.times(1))
                .findByNameIgnoreCase(Mockito.anyString());
    }

}
