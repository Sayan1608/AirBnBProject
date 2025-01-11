package com.codingshuttle.projects.services;

import com.codingshuttle.projects.entities.Room;

public interface InventoryService {
    void createInventoryForRoom(Room room);
    void deleteInventoriesForRoom(Room room);

}
