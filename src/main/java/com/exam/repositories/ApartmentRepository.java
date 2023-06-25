package com.exam.repositories;

import com.exam.entities.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    List<Apartment> findApartmentByIdIsNotIn(List<Long> ids);
    List<Apartment> findApartmentByIdIsIn(List<Long> ids);
}
