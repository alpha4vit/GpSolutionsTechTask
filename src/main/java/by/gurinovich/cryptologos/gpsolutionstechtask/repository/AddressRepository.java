package by.gurinovich.cryptologos.gpsolutionstechtask.repository;

import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Address;
import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    @Query(value = "select a.city, count(h.id) from addresses a join public.hotels h on h.address_id = a.id group by a.city", nativeQuery = true)
    List<Object[]> groupCities();

    @Query(value = "select c.name, count(h.id) from addresses a join public.hotels h on h.address_id = a.id join countries c on c.id = a.country_id group by c.id", nativeQuery = true)
    List<Object[]> groupCountries();

}
