package com.exam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Data
public class ApartmentDto {
    private int apartmentNumber;
    private int oneDayPrice;
}
