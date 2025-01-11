package com.codingshuttle.projects.services;

import com.codingshuttle.projects.dtos.BookingRequest;
import com.codingshuttle.projects.dtos.HotelDto;
import com.codingshuttle.projects.dtos.HotelSearchRequest;
import com.codingshuttle.projects.entities.Hotel;
import com.codingshuttle.projects.entities.Inventory;
import com.codingshuttle.projects.entities.Room;
import com.codingshuttle.projects.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService{
    private final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;

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
    public void deleteInventoriesForRoom(Room room) {
        inventoryRepository.deleteByRoom(room);
    }

    @Override
    public Page<HotelDto> searchHotelBasedOnInventory(HotelSearchRequest hotelSearchRequest) {
        Pageable pageable = PageRequest.of(hotelSearchRequest.getPage(),hotelSearchRequest.getSize());
        long daysCount =
                ChronoUnit.DAYS.between(hotelSearchRequest.getStartDate(), hotelSearchRequest.getEndDate()) + 1;

        Page<Hotel> hotelPage = inventoryRepository.getHotelByInventories(hotelSearchRequest.getCity(), hotelSearchRequest.getStartDate()
                , hotelSearchRequest.getEndDate(), daysCount, hotelSearchRequest.getRoomsCount(), pageable);

        return hotelPage.map(hotel -> modelMapper.map(hotel, HotelDto.class));
    }

}
