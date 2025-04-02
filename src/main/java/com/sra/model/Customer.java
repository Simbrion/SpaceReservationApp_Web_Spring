package com.sra.model;

import com.sra.repository.ReservationsRepository;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;


@Data
@Component
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name",length=20, unique=true, nullable=false)
    private String name;



    public List<Reservation> getReservations(ReservationsRepository reservationsRepository) {
        List<Reservation> result = new ArrayList<>();
        for (Reservation reservation : reservationsRepository.getData()) {
            if (reservation.getCustomer().getName().equals(this.name)) result.add(reservation);
        }
        return result;
    }

}
