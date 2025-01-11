package com.codingshuttle.projects.services;

import com.codingshuttle.projects.dtos.HotelDto;
import com.codingshuttle.projects.dtos.HotelInfoDto;
import com.codingshuttle.projects.entities.Hotel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HotelService {
    HotelDto createNewHotel(HotelDto hotelDto);
    HotelDto getHotelById(Long id);
    HotelDto updateHotelById(Long id, HotelDto hotelDto);
    void deleteHotelById(Long id);
    void activateHotel(Long id);
    Hotel isHotelExistsById(Long id);

    List<HotelDto> getAllHotels();

    HotelInfoDto getHotelInfo(Long hotelId);
}
