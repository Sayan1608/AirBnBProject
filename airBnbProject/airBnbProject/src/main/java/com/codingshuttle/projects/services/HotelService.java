package com.codingshuttle.projects.services;

import com.codingshuttle.projects.dtos.HotelDto;
import com.codingshuttle.projects.entities.Hotel;

public interface HotelService {
    Hotel createNewHotel(HotelDto hotelDto);
    Hotel getHotelById(Long id);
}
