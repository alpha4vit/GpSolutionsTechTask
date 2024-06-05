package by.gurinovich.cryptologos.gpsolutionstechtask.repository;

import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Address;
import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByHouseNumberAndStreetAndCityAndCountryAndPostCode(
            String houseNumber,
            String street,
            String city,
            Country country,
            String postCode
    );

}
