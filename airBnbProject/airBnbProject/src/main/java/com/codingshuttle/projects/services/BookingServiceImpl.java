package com.codingshuttle.projects.services;

import com.codingshuttle.projects.dtos.BookingDto;
import com.codingshuttle.projects.dtos.BookingRequest;
import com.codingshuttle.projects.entities.*;
import com.codingshuttle.projects.entities.enums.BookingStatus;
import com.codingshuttle.projects.exceptions.ResourceNotFoundException;
import com.codingshuttle.projects.repositories.BookingRepository;
import com.codingshuttle.projects.repositories.HotelRepository;
import com.codingshuttle.projects.repositories.InventoryRepository;
import com.codingshuttle.projects.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final InventoryRepository inventoryRepository;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public BookingDto initialiseBooking(BookingRequest bookingRequest) {


        long dateCount = ChronoUnit.DAYS.between(bookingRequest.getCheckInDate(), bookingRequest.getCheckOutDate()) + 1;
        log.info("Initialising booking for hotel {}, room {} between {} to {}"
                ,bookingRequest.getHotelId(),bookingRequest.getRoomId()
                ,bookingRequest.getCheckInDate(),bookingRequest.getCheckOutDate());

        Hotel hotel = hotelRepository.findById(bookingRequest.getHotelId())
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id " + bookingRequest.getHotelId()));

        Room room = roomRepository.findById(bookingRequest.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id " + bookingRequest.getRoomId()));

        List<Inventory> inventories = inventoryRepository
                .findAndLockAvailableInventories(bookingRequest.getRoomId(),
                bookingRequest.getCheckInDate(),bookingRequest.getCheckOutDate(),bookingRequest.getRoomsCount());

        if(inventories.size() != dateCount){
            throw new IllegalStateException("Room No more Available");
        }
        // Reserve the room/update the reserved count of the Inventories
        inventories.forEach(inventory -> {
            inventory.setReservedCount((int) (inventory.getReservedCount() + bookingRequest.getRoomsCount()));
        });

        inventoryRepository.saveAll(inventories);

        // create booking

        User user = new User();
        user.setId(1L);

        Booking booking = Booking.builder()
                .bookingStatus(BookingStatus.RESERVED)
                .hotel(hotel)
                .room(room)
                .checkInDate(bookingRequest.getCheckInDate())
                .checkOutDate(bookingRequest.getCheckOutDate())
                .roomsCount(Math.toIntExact(bookingRequest.getRoomsCount()))
                .user(user)
                .amount(BigDecimal.TEN)
                .build();

        bookingRepository.save(booking);
        return modelMapper.map(booking,BookingDto.class);
    }
}
