package by.gurinovich.cryptologos.gpsolutionstechtask.service;


import by.gurinovich.cryptologos.gpsolutionstechtask.entity.Address;

public interface AddressService {

    Address getOrSave(Address address);

}
