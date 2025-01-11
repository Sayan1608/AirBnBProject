package com.codingshuttle.projects.services;

import com.codingshuttle.projects.dtos.BookingDto;
import com.codingshuttle.projects.dtos.BookingRequest;

public interface BookingService {
    BookingDto initialiseBooking(BookingRequest bookingRequest);
}
