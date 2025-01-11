package com.codingshuttle.projects.services;

import com.codingshuttle.projects.dtos.HotelDto;
import com.codingshuttle.projects.entities.Hotel;
import com.codingshuttle.projects.entities.Room;
import com.codingshuttle.projects.exceptions.ResourceNotFoundException;
import com.codingshuttle.projects.repositories.HotelRepository;
import com.codingshuttle.projects.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService{
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final InventoryService inventoryService;
    private final RoomRepository roomRepository;

    public HotelDto createNewHotel(HotelDto hotelDto){
        log.info("Creating a new hotel with name: {}",hotelDto.getName());
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        hotel.setActive(false);
        Hotel savedHotel = hotelRepository.save(hotel);
        return modelMapper.map(savedHotel, HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long id) {
        log.info("Searching hotel with ID: {}",id );
        Hotel hotel = isHotelExistsById(id);
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto updateHotelById(Long id, HotelDto hotelDto) {
        log.info("Updating hotel with ID: {}",id);
        Hotel hotel = isHotelExistsById(id);
        modelMapper.map(hotelDto,hotel);
        hotel.setId(id);
        Hotel updatedHotel = hotelRepository.save(hotel);
        return modelMapper.map(updatedHotel, HotelDto.class);
    }

    @Transactional
    @Override
    public void deleteHotelById(Long id) {
        log.info("Deleting hotel with ID: {}",id);
        Hotel hotel = isHotelExistsById(id);

        //TODO : delete inventory for this hotel
        for(Room room : hotel.getRooms()){
            inventoryService.deleteInventoriesForRoom(room);
            roomRepository.deleteById(room.getId());
        }
        hotelRepository.deleteById(id);
    }

    @Override
    public void activateHotel(Long id) {
        log.info("Activating hotel with ID: {}",id);
        Hotel hotel = isHotelExistsById(id);
        hotel.setActive(true);

        //create inventory for all rooms of this hotel

        for(Room room : hotel.getRooms()){
            inventoryService.createInventoryForRoom(room);
        }
    }

    @Override
    public Hotel isHotelExistsById(Long id){
        boolean exists = hotelRepository.existsById(id);
        if(!exists) throw new ResourceNotFoundException("Hotel not found with Id: " + id);
        return hotelRepository.findById(id).get();
    }

    @Override
    public List<HotelDto> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels
                .stream()
                .map(hotel -> modelMapper.map(hotel, HotelDto.class))
                .collect(Collectors.toList());

    }
}
