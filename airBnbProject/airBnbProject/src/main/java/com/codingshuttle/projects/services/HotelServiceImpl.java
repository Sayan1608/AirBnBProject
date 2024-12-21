package com.codingshuttle.projects.services;

import com.codingshuttle.projects.dtos.HotelDto;
import com.codingshuttle.projects.entities.Hotel;
import com.codingshuttle.projects.repositories.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService{
    private final HotelRepository hotelRepository;

    public Hotel createNewHotel(HotelDto hotelDto){
        log.info("Creating new hotel with name {}",hotelDto.getName());
    }

    @Override
    public Hotel getHotelById(Long id) {
        return null;
    }
}
