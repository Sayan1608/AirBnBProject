package com.codingshuttle.projects.services;

import com.codingshuttle.projects.dtos.BookingRequest;
import com.codingshuttle.projects.dtos.HotelDto;
import com.codingshuttle.projects.dtos.HotelSearchRequest;
import com.codingshuttle.projects.entities.Inventory;
import com.codingshuttle.projects.entities.Room;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InventoryService {
    void createInventoryForRoom(Room room);
    void deleteInventoriesForRoom(Room room);
    Page<HotelDto> searchHotelBasedOnInventory(HotelSearchRequest hotelSearchRequest);

}
