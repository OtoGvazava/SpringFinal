package com.exam.entities;

import com.exam.dto.HotelDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hotel")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class Hotel extends AppEntity<Long> {
    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "hotelIdSeq", sequenceName = "HOTEL_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotelIdSeq")
    private Long id;

    @Column(name = "NAME")
    private String name;

    public Hotel(HotelDto hotelDto) {
        this.name = hotelDto.getName();
    }
}
