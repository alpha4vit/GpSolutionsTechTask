package by.gurinovich.cryptologos.gpsolutionstechtask.controller;

import by.gurinovich.cryptologos.gpsolutionstechtask.dto.HotelDTO;
import by.gurinovich.cryptologos.gpsolutionstechtask.dto.HotelSummaryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HotelController {

    @GetMapping("/hotels")
    public ResponseEntity<List<HotelSummaryDTO>> getHotels(){
        return ResponseEntity.ok(List.of()); //TODO
    }

    @GetMapping("/hotels/{id}")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable("id") Long id){
        return ResponseEntity.ok(null); //TODO
    }
}
