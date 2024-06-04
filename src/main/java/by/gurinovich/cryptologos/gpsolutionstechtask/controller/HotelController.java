package by.gurinovich.cryptologos.gpsolutionstechtask.controller;

import by.gurinovich.cryptologos.gpsolutionstechtask.dto.HotelDTO;
import by.gurinovich.cryptologos.gpsolutionstechtask.dto.HotelSummaryDTO;
import by.gurinovich.cryptologos.gpsolutionstechtask.service.HotelService;
import by.gurinovich.cryptologos.gpsolutionstechtask.util.mapper.HotelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;
    private final HotelMapper hotelMapper;

    @GetMapping("/hotels")
    public ResponseEntity<List<HotelSummaryDTO>> getHotels(){
        var hotels = hotelService.getAll();
        return ResponseEntity.ok(hotelMapper.toSummaryDTOs(hotels));
    }

    @GetMapping("/hotels/{id}")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable("id") Long id){
        var hotel = hotelService.getById(id);
        return ResponseEntity.ok(hotelMapper.toDTO(hotel));
    }
}
