package by.gurinovich.cryptologos.gpsolutionstechtask.service.impl;

import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Address;
import by.gurinovich.cryptologos.gpsolutionstechtask.repository.AddressRepository;
import by.gurinovich.cryptologos.gpsolutionstechtask.service.AddressService;
import by.gurinovich.cryptologos.gpsolutionstechtask.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final CountryService countryService;

    @Override
    public Address getOrSave(Address address) {
        var country = countryService.getOrSave(address.getCountry());
        var check = addressRepository.findByHouseNumberAndStreetAndCityAndCountryAndPostCode(
                address.getHouseNumber(),
                address.getStreet(),
                address.getCity(),
                country,
                address.getPostCode()
        );
        return check.orElseGet(() -> addressRepository.save(address));
    }
}
