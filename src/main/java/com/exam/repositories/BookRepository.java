package com.exam.repositories;

import com.exam.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBooksByStartDateBetweenOrEndDateBetween(Date startDate, Date startDate2, Date endDate, Date endDate2);
}
