package com.codingshuttle.projects.repositories;

import com.codingshuttle.projects.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {
}
