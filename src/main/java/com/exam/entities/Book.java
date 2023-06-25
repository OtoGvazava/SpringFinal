package com.exam.entities;

import com.exam.dto.BookDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "book")
@NoArgsConstructor
@Data
public class Book extends AppEntity<Long> {
    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "bookIdSeq", sequenceName = "BOOK_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookIdSeq")
    private Long id;
    @Column(name = "FULL_NAME")
    private String fullName;
    @Column(name = "PERSONAL_NUMBER")
    private String personalNumber;
    @ManyToOne
    private Apartment apartment;
    @ManyToOne
    private Hotel hotel;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "END_DATE")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    public Book(BookDto bookDto, Apartment apartment, Hotel hotel) {
        this.fullName = bookDto.getFullName();
        this.personalNumber = bookDto.getPersonalNumber();
        this.startDate = bookDto.getStartDate();
        this.endDate = bookDto.getEndDate();
        this.apartment = apartment;
        this.hotel = hotel;
    }

}
