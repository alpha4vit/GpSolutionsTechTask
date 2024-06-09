package by.gurinovich.cryptologos.gpsolutionstechtask.service.impl;

import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Country;
import by.gurinovich.cryptologos.gpsolutionstechtask.repository.CountryRepository;
import by.gurinovich.cryptologos.gpsolutionstechtask.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    public Country getOrSave(Country country) {
        var check = countryRepository.findByNameIgnoreCase(country.getName());
        return check.orElseGet(() -> countryRepository.save(country));
    }
}
