package com.exam.entities;

import com.exam.data.RecordState;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;

@Data
@MappedSuperclass
public abstract class AppEntity<ID extends Serializable> {
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE")
    private String createdDate;
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_MODIFIED_DATE")
    private String lastModifiedDate;
    @Column(name = "RECORD_STATE")
    @Enumerated(EnumType.ORDINAL)
    private RecordState recordState;
}
