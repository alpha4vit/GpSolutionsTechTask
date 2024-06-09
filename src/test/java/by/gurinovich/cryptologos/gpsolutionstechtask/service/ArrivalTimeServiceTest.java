package by.gurinovich.cryptologos.gpsolutionstechtask.service;

import by.gurinovich.cryptologos.gpsolutionstechtask.entity.ArrivalTime;
import by.gurinovich.cryptologos.gpsolutionstechtask.repository.ArrivalTimeRepository;
import by.gurinovich.cryptologos.gpsolutionstechtask.service.impl.ArrivalTimeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ArrivalTimeServiceTest {

    @Mock
    private ArrivalTimeRepository arrivalTimeRepository;

    @InjectMocks
    private ArrivalTimeServiceImpl arrivalTimeService;

    private ArrivalTime arrivalTime;

    @BeforeEach
    void setUp(){
        this.arrivalTime = ArrivalTime.builder()
                .id(1L)
                .start(LocalTime.MIN)
                .end(LocalTime.MAX)
                .build();
    }

    @Test
    void getExistedObjectWhenGerOrSave() {
        Mockito.when(arrivalTimeRepository.findByStartAndEnd(Mockito.any(LocalTime.class), Mockito.any(LocalTime.class)))
                .thenReturn(Optional.of(arrivalTime));
        final var res = arrivalTimeService.getOrSave(arrivalTime);
        Assertions.assertEquals(arrivalTime, res);
        Mockito.verify(arrivalTimeRepository, Mockito.times(0))
                .save(Mockito.any(ArrivalTime.class));
        Mockito.verify(arrivalTimeRepository, Mockito.times(1))
                .findByStartAndEnd(Mockito.any(LocalTime.class), Mockito.any(LocalTime.class));
    }

    @Test
    void saveObjectWhenGerOrSave() {
        Mockito.when(arrivalTimeRepository.findByStartAndEnd(Mockito.any(LocalTime.class), Mockito.any(LocalTime.class)))
                .thenReturn(Optional.empty());
        Mockito.when(arrivalTimeRepository.save(Mockito.any(ArrivalTime.class)))
                .thenReturn(arrivalTime);
        final var res = arrivalTimeService.getOrSave(arrivalTime);
        Assertions.assertEquals(arrivalTime, res);
        Mockito.verify(arrivalTimeRepository, Mockito.times(1))
                .save(arrivalTime);
        Mockito.verify(arrivalTimeRepository, Mockito.times(1))
                .findByStartAndEnd(Mockito.any(LocalTime.class), Mockito.any(LocalTime.class));
    }

}
