package com.exam.entities;

import com.exam.dto.ApartmentDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Table(name = "apartment")
@Data
public class Apartment extends AppEntity<Long> {
    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "apartmentIdSeq", sequenceName = "APARTMENT_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apartmentIdSeq")
    private Long id;
    @Column(unique = true, name = "APARTMENT_NUMBER")
    private int apartmentNumber;
    @Column(name = "ONE_DAY_PRICE")
    private int oneDayPrice;
    @ManyToOne
    private Hotel hotel;

    public Apartment(ApartmentDto apartmentDto, Hotel hotel) {
        this.apartmentNumber = apartmentDto.getApartmentNumber();
        this.oneDayPrice = apartmentDto.getOneDayPrice();
        this.hotel = hotel;
    }
}
