package com.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;


@Data
public class ReservationDto {

    @Id
    private int id;

    @Column
    private String spaceName;

    @Column
    private String customerName;

    @Column(name = "date", nullable = false)
    private LocalDate date;


    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;


}
