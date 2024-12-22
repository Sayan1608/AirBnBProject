package com.codingshuttle.projects.services;

import com.codingshuttle.projects.entities.Inventory;
import com.codingshuttle.projects.entities.Room;
import com.codingshuttle.projects.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService{
    private final InventoryRepository inventoryRepository;

    @Override
    public void createInventoryForRoom(Room room) {
        LocalDate today = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusYears(1);

        for(;!today.isAfter(endDate);today = today.plusDays(1)){
            Inventory inventory = Inventory
                    .builder()
                    .room(room)
                    .hotel(room.getHotel())
                    .bookedCount(0)
                    .totalCount(room.getTotalCount())
                    .city(room.getHotel().getCity())
                    .closed(false)
                    .surgeFactor(BigDecimal.valueOf(1))
                    .date(today)
                    .price(room.getBasePrice())
                    .build();
            inventoryRepository.save(inventory);
        }
    }

    @Override
    public void deleteFutureInventoriesForRoom(Room room) {
        inventoryRepository.deleteByDateAfterAndRoom(LocalDate.now(),room);
    }
}
