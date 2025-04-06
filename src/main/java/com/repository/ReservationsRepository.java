package com.repository;

import com.model.Reservation;
import com.model.ReservationDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class ReservationsRepository extends DataRepository<Reservation> {


    @Autowired
    public ReservationsRepository(EntityManager em) {
        super(em);
    }

    public boolean isEmpty() {
        TypedQuery<Long> query = super.getEntityManager().createQuery("SELECT COUNT(r) FROM Reservation r", Long.class);
        return  super.isEmpty(query);
    }

    public List<Reservation> getData() {
        TypedQuery<Reservation> query = super.getEntityManager().createQuery("SELECT r FROM Reservation r", Reservation.class);
        return query.getResultList();
    }

    public Reservation getReservationById(int reservationId) {
        return super.getEntityManager().find(Reservation.class, reservationId);
    }

    @Transactional
    public void removeReservation (Reservation reservation)  {
        String stringQuery = "DELETE FROM Reservation r WHERE r.id = :id";
        Query query = super.getEntityManager().createQuery(stringQuery);
        query.setParameter("id", reservation.getId());
        query.executeUpdate();
    }

    public List<ReservationDto> getReservationsDtoListByCustomerId (Integer customerId) {
        List<ReservationDto> customerReservationsDto = new ArrayList<>();
        for (Reservation reservation : this.getData()) {
            if (((Integer) reservation.getCustomer().getId()).equals(customerId)) {
                ReservationDto reservationDto = new ReservationDto();
                reservationDto.setId(reservation.getId());
                reservationDto.setCustomerName(reservation.getCustomer().getName());
                reservationDto.setSpaceName(reservation.getSpace().getName());
                reservationDto.setDate(reservation.getDate());
                reservationDto.setStartTime(reservation.getStartTime());
                reservationDto.setEndTime(reservation.getEndTime());
                customerReservationsDto.add(reservationDto);
            }
        }
        return customerReservationsDto;
    }

    public List<ReservationDto> getReservationsDtoList () {
        List<ReservationDto> customerReservationsDto = new ArrayList<>();
        for (Reservation reservation : this.getData()) {
                ReservationDto reservationDto = new ReservationDto();
                reservationDto.setId(reservation.getId());
                reservationDto.setCustomerName(reservation.getCustomer().getName());
                reservationDto.setSpaceName(reservation.getSpace().getName());
                reservationDto.setDate(reservation.getDate());
                reservationDto.setStartTime(reservation.getStartTime());
                reservationDto.setEndTime(reservation.getEndTime());
                customerReservationsDto.add(reservationDto);
            }
        return customerReservationsDto;
        }

    @Transactional
    public void addReservation(Reservation reservation) {
        super.addEntity(reservation);
    }

}
