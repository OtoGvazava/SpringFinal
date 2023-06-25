package com.exam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String fullName;
    private String personalNumber;
    private Date startDate;
    private Date endDate;
}
