package com.exam.controllers;

import com.exam.dto.ApartmentDto;
import com.exam.dto.ApiResponse;
import com.exam.dto.BookDto;
import com.exam.dto.HotelDto;
import com.exam.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class HotelController {
    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("/hotel")
    public ApiResponse addHotel(@RequestBody HotelDto hotelDto) {
        return hotelService.addHotel(hotelDto);
    }

    @PostMapping("/hotel/{id}")
    public ApiResponse addApartment(@PathVariable long id, @RequestBody ApartmentDto apartmentDto) {
        return hotelService.addApartment(apartmentDto, id);
    }

    @PostMapping("hotel/{hotelId}/{apartmentId}")
    public ApiResponse addBook(@PathVariable long hotelId, @PathVariable long apartmentId, @RequestBody BookDto bookDto) {
        return hotelService.addBook(bookDto, apartmentId, hotelId);
    }

    @GetMapping("books")
    public ApiResponse getBooks() {
        return hotelService.getBooks();
    }

    @GetMapping("apartments/{booked}")
    public ApiResponse getApartments(@PathVariable boolean booked,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        if (booked) {
            return hotelService.getBookedApartments(startDate, endDate);
        } else return hotelService.getUnbookedApartments(startDate, endDate);
    }

    @GetMapping("apartments")
    public ApiResponse getApartments() {
        return hotelService.getApartments();
    }
}
