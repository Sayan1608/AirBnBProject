package com.codingshuttle.projects.repositories;

import com.codingshuttle.projects.entities.Inventory;
import com.codingshuttle.projects.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    void deleteByRoom(Room room);
}
