package com.exam.services;

import com.exam.dto.ApartmentDto;
import com.exam.dto.ApiResponse;
import com.exam.dto.BookDto;
import com.exam.dto.HotelDto;
import com.exam.entities.Apartment;
import com.exam.entities.Book;
import com.exam.entities.Hotel;
import com.exam.repositories.ApartmentRepository;
import com.exam.repositories.BookRepository;
import com.exam.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private final ApartmentRepository apartmentRepository;
    private final BookRepository bookRepository;

    @Autowired
    HotelService(HotelRepository hotelRepository, ApartmentRepository apartmentRepository, BookRepository bookRepository) {
        this.hotelRepository = hotelRepository;
        this.apartmentRepository = apartmentRepository;
        this.bookRepository = bookRepository;
    }

    public ApiResponse addHotel(HotelDto hotelDto) {
        return new ApiResponse("hotel", this.hotelRepository.save(new Hotel(hotelDto)));
    }

    public ApiResponse addApartment(ApartmentDto apartmentDto, long hotelId) {
        Optional<Hotel> optionalHotel = this.hotelRepository.findById(hotelId);
        return optionalHotel.map(hotel -> new ApiResponse("apartment", this.apartmentRepository.save(new Apartment(apartmentDto, hotel))))
                .orElseGet(() -> new ApiResponse().addError("invalid_hotel_id", hotelId));
    }

    public ApiResponse addBook(BookDto bookDto, long apartmentId, long hotelId) {
        Optional<Hotel> optionalHotel = this.hotelRepository.findById(hotelId);
        Optional<Apartment> optionalApartment = this.apartmentRepository.findById(apartmentId);
        if (optionalHotel.isPresent() && optionalApartment.isPresent()) {
            return new ApiResponse("book", this.bookRepository.save(new Book(bookDto, optionalApartment.get(), optionalHotel.get())));
        } else return new ApiResponse().addError("error", "error");
    }

    public ApiResponse getBooks() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.addData("books", this.bookRepository.findAll());
        this.bookRepository.findAll().forEach(entitie-> System.out.println(entitie.toString()));
        return apiResponse;
    }

    public List<Book> getBooksByDate(Date startDate, Date endDate) {
        return this.bookRepository.findBooksByStartDateBetweenOrEndDateBetween(startDate, endDate, startDate, endDate);
    }

    public ApiResponse getUnbookedApartments(Date startDate, Date endDate) {
        List<Apartment> apartments = this.apartmentRepository.findApartmentByIdIsNotIn(getBooksByDate(startDate, endDate).stream().map(book -> {
            if (book.getApartment() != null) {
                return book.getApartment().getId();
            } return 0L;
        }).toList());
        return new ApiResponse("apartments", apartments);
    }

    public ApiResponse getBookedApartments(Date startDate, Date endDate) {
        List<Apartment> apartments = this.apartmentRepository.findApartmentByIdIsIn(getBooksByDate(startDate, endDate).stream().map(book -> {
            if (book.getApartment() != null) {
                return book.getApartment().getId();
            } return 0L;
        }).toList());
        return new ApiResponse("apartments", apartments);
    }

    public ApiResponse getApartments() {
        List<Apartment> apartments = this.apartmentRepository.findAll();
        return new ApiResponse("apartments", apartments);
    }
}
