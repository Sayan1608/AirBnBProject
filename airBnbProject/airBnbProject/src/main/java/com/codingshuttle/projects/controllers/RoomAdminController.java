package com.codingshuttle.projects.controllers;

import com.codingshuttle.projects.dtos.RoomDto;
import com.codingshuttle.projects.services.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/hotels")
@RequiredArgsConstructor
@Slf4j
public class RoomAdminController {
    private final RoomService roomService;

    @PostMapping(path = "/{hotelId}/rooms")
    public ResponseEntity<RoomDto> createNewHotel(
            @PathVariable Long hotelId, @RequestBody RoomDto roomDto){
        log.info("Attempting to create a new room in hotel with ID: {}",hotelId);
        RoomDto newRoom = roomService.createNewRoom(hotelId, roomDto);
        return new ResponseEntity<>(newRoom, HttpStatus.CREATED);
    }

    @GetMapping(path = "/rooms/{hotelId}")
    public ResponseEntity<List<RoomDto>> getAllRoomsInHotel(@PathVariable Long hotelId){
        log.info("Attempting to fetch all rooms in hotel with ID: {}",hotelId);
        List<RoomDto> allRoomsInHotel = roomService.getAllRoomsInHotel(hotelId);
        return ResponseEntity.ok(allRoomsInHotel);
    }

    @GetMapping(path = "/room/{roomId}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable Long roomId){
        log.info("Attempting to fetch room with ID: {}",roomId);
        RoomDto roomById = roomService.getRoomById(roomId);
        return ResponseEntity.ok(roomById);
    }

    @DeleteMapping(path = "/room/{roomId}")
    public ResponseEntity<RoomDto> deleteRoomById(@PathVariable Long roomId){
        log.info("Attempting to delete room with ID: {}",roomId);
       roomService.deleteRoomById(roomId);
        return ResponseEntity.noContent().build();
    }
}
