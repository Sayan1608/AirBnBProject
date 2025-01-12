package com.codingshuttle.projects.services;

import com.codingshuttle.projects.dtos.BookingDto;
import com.codingshuttle.projects.dtos.BookingRequest;
import com.codingshuttle.projects.dtos.GuestDto;

import java.util.List;

public interface BookingService {
    BookingDto initialiseBooking(BookingRequest bookingRequest);

    BookingDto addGuestsToBooking(Long bookingId, List<GuestDto> guestDtoList);
}
