package com.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;


@Data
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "space_id", nullable = false)
    private Space space;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @NotNull (message = "Please choose the date.")
    @FutureOrPresent (message = "  The date cannot be in the past.")
    @Column(name = "date", nullable = false)
    private LocalDate date;


    @NotNull (message = "Please choose the time.")
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @NotNull (message = "Please choose the time.")
    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

}
