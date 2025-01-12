package com.codingshuttle.projects.controllers;

import com.codingshuttle.projects.dtos.BookingDto;
import com.codingshuttle.projects.dtos.BookingRequest;
import com.codingshuttle.projects.dtos.GuestDto;
import com.codingshuttle.projects.services.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
@Slf4j
public class HotelBookingController {
    private final BookingService bookingService;

    @PostMapping(path = "/init")
    public ResponseEntity<BookingDto> initialiseBooking(@RequestBody BookingRequest bookingRequest){
        return ResponseEntity.ok(bookingService.initialiseBooking(bookingRequest));
    }

    @PostMapping(path = "/{bookingId}/addGuests")
    public ResponseEntity<BookingDto> addGuestsToBooking(
            @PathVariable(name = "bookingId") Long bookingId, @RequestBody List<GuestDto> guestDtoList){
        BookingDto bookingDto = bookingService.addGuestsToBooking(bookingId,guestDtoList);
        return ResponseEntity.ok(bookingDto);
    }
}
