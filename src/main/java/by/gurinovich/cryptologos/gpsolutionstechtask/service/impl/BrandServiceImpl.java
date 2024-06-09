package by.gurinovich.cryptologos.gpsolutionstechtask.service.impl;

import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Brand;
import by.gurinovich.cryptologos.gpsolutionstechtask.repository.BrandRepository;
import by.gurinovich.cryptologos.gpsolutionstechtask.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public Brand getOrSave(Brand brand) {
        var check = brandRepository.findByNameIgnoreCase(brand.getName());
        return check.orElseGet(() -> brandRepository.save(brand));
    }
}
