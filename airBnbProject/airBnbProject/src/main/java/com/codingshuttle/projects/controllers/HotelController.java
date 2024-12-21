package com.codingshuttle.projects.controllers;

import com.codingshuttle.projects.dtos.HotelDto;
import com.codingshuttle.projects.services.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin/hotels")
@RequiredArgsConstructor
@Slf4j
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelDto> createNewHotel(@RequestBody HotelDto hotelDto){
        log.info("Attempting to create a new Hotel with name: {}",hotelDto.getName());
        HotelDto newHotel = hotelService.createNewHotel(hotelDto);
        return new ResponseEntity<>(newHotel, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{hotelId}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable Long hotelId){
        log.info("Attempting to fetch hotel with id: {}",hotelId);
        return ResponseEntity.ok(hotelService.getHotelById(hotelId));
    }

    @PutMapping(path = "/{hotelId}")
    public ResponseEntity<HotelDto> updateHotelById(
            @PathVariable Long hotelId, @RequestBody HotelDto hotelDto ){
        log.info("Attempting to update hotel with id: {}",hotelId);
        return ResponseEntity.ok(hotelService.updateHotelById(hotelId,hotelDto));
    }

    @DeleteMapping(path = "/{hotelId}")
    public ResponseEntity<HotelDto> deleteHotelById(@PathVariable Long hotelId){
        log.info("Attempting to delete hotel with id: {}",hotelId);
        hotelService.deleteHotelById(hotelId);
        return ResponseEntity.noContent().build();
    }


}
