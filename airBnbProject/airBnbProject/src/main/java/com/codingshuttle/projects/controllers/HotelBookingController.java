package com.codingshuttle.projects.controllers;

import com.codingshuttle.projects.dtos.BookingDto;
import com.codingshuttle.projects.dtos.BookingRequest;
import com.codingshuttle.projects.services.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
