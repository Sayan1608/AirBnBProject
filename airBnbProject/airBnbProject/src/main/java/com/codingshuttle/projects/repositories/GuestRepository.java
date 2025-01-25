package com.codingshuttle.projects.repositories;

import com.codingshuttle.projects.entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}