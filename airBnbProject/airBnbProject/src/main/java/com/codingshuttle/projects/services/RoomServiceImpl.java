package com.codingshuttle.projects.services;

import com.codingshuttle.projects.dtos.RoomDto;
import com.codingshuttle.projects.entities.Hotel;
import com.codingshuttle.projects.entities.Room;
import com.codingshuttle.projects.exceptions.ResourceNotFoundException;
import com.codingshuttle.projects.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService{
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    private final HotelService hotelService;
    private final InventoryService inventoryService;

    @Override
    public RoomDto createNewRoom(Long hotelId, RoomDto roomDto) {
        log.info("Creating a new room in hotel with ID: {}",hotelId);
        Hotel hotel = hotelService.isHotelExistsById(hotelId);
        Room room = modelMapper.map(roomDto, Room.class);
        room.setHotel(hotel);
        room = roomRepository.save(room);
        //create inventory for this room if hotel is active

        if(hotel.getActive()){
            inventoryService.createInventoryForRoom(room);
        }

        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {
        log.info("Fetching all rooms in hotel with ID: {}",hotelId);
        Hotel hotel = hotelService.isHotelExistsById(hotelId);
        return hotel.getRooms()
                .stream()
                .map(room->modelMapper.map(room, RoomDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoomById(Long roomId) {
        log.info("Fetching room with ID : {}", roomId);
        Room room = exitsRoomById(roomId);
        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    public void deleteRoomById(Long roomId) {
        log.info("Deleting room with ID : {}", roomId);
        boolean exists = roomRepository.existsById(roomId);
        Room room = exitsRoomById(roomId);
        roomRepository.delete(room);
        //TODO : delete all future inventories for this room
    }

    private Room exitsRoomById(Long roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: " + roomId));
    }
}
