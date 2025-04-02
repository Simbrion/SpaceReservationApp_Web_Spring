package com.sra.model;

import jakarta.persistence.*;
import com.sra.config.Config;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


@Data
@Component
@Entity
@Table(name = "reservation")
public class Reservation implements Describable, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "space_id", nullable = false)
    private Space space;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;


    public void printDescription() {
        System.out.printf(Config.YELLOW_COLOUR + " -Reservation id: %d, reserved by %s, reserved space: %s, reserved slot: %tF, %tR-%tR." + Config.RESET_COLOUR + "%n",
                this.getId(),
                this.customer.getName(),
                this.getSpace().getName(),
                this.getDate(),
                this.getStartTime(),
                this.getEndTime());
    }

}
