package com.codingshuttle.projects.services;

import com.codingshuttle.projects.dtos.HotelDto;
import com.codingshuttle.projects.entities.Hotel;

public interface HotelService {
    HotelDto createNewHotel(HotelDto hotelDto);
    HotelDto getHotelById(Long id);
    HotelDto updateHotelById(Long id, HotelDto hotelDto);
    void deleteHotelById(Long id);
    void activateHotel(Long id);
    Hotel isHotelExistsById(Long id);
}
