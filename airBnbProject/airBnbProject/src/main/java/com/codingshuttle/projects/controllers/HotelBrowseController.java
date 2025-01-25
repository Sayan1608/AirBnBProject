package com.codingshuttle.projects.controllers;

import com.codingshuttle.projects.dtos.HotelDto;
import com.codingshuttle.projects.dtos.HotelInfoDto;
import com.codingshuttle.projects.dtos.HotelPriceDto;
import com.codingshuttle.projects.dtos.HotelSearchRequest;
import com.codingshuttle.projects.services.HotelService;
import com.codingshuttle.projects.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelBrowseController {
    private final InventoryService inventoryService;
    private final HotelService hotelService;

    @GetMapping("/search")
    public ResponseEntity<Page<HotelPriceDto>> searchHotel(@RequestBody HotelSearchRequest hotelSearchRequest){
        var hotelDtoPage = inventoryService.searchHotelBasedOnInventory(hotelSearchRequest);
        return ResponseEntity.ok(hotelDtoPage);
    }

    @GetMapping("/{hotelId}/info")
    public ResponseEntity<HotelInfoDto> getHotelInfo(@PathVariable("hotelId") Long hotelId){
        return ResponseEntity.ok(hotelService.getHotelInfo(hotelId));
    }
}
