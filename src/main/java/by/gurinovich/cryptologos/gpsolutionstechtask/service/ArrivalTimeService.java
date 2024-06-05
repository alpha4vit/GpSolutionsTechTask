package by.gurinovich.cryptologos.gpsolutionstechtask.service;

import by.gurinovich.cryptologos.gpsolutionstechtask.entity.ArrivalTime;

public interface ArrivalTimeService {

    ArrivalTime getOrSave(ArrivalTime arrivalTime);

}
