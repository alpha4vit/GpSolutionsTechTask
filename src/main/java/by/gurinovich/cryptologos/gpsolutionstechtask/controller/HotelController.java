package by.gurinovich.cryptologos.gpsolutionstechtask.controller;

import by.gurinovich.cryptologos.gpsolutionstechtask.dto.HotelDTO;
import by.gurinovich.cryptologos.gpsolutionstechtask.dto.HotelFilterDTO;
import by.gurinovich.cryptologos.gpsolutionstechtask.dto.HotelSummaryDTO;
import by.gurinovich.cryptologos.gpsolutionstechtask.repository.AmenityRepository;
import by.gurinovich.cryptologos.gpsolutionstechtask.service.HotelService;
import by.gurinovich.cryptologos.gpsolutionstechtask.util.mapper.AmenityMapper;
import by.gurinovich.cryptologos.gpsolutionstechtask.util.mapper.HotelMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;
    private final HotelMapper hotelMapper;
    private final AmenityMapper amenityMapper;
    private final AmenityRepository amenityRepository;

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

    @PostMapping("/hotels")
    public ResponseEntity<HotelSummaryDTO> createHotel(@RequestBody HotelDTO hotelDTO){
        var saved = hotelService.save(hotelMapper.toEntity(hotelDTO));
        return ResponseEntity.ok(hotelMapper.toSummaryDTO(saved));
    }

    @PostMapping("/hotels/{id}/amenities")
    public ResponseEntity<HotelDTO> addAmenitiesToHotel(@PathVariable("id") Long id,
                                                        @RequestBody List<String> amenitiesStr){
        var amenities = amenityMapper.toEntities(amenitiesStr);
        var hotel = hotelService.addAmenities(id, amenities);
        return ResponseEntity.ok(hotelMapper.toDTO(hotel));
    }

    @GetMapping("/histogram/{param}")
    public ResponseEntity<Map<String, Long>> histogram(@PathVariable("param") String param){
        var res = amenityRepository.groupAmenityByParam(param);
        return ResponseEntity.ok(null); //TODO
    }

    @GetMapping("/search")
    public ResponseEntity<List<HotelSummaryDTO>> findBySearchParam(@RequestParam Map<String, String> filter){
        var res = hotelService.getAll(HotelFilterDTO.of(filter));
        return ResponseEntity.ok(hotelMapper.toSummaryDTOs(res));
    }

}

