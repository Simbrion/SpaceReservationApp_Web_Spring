package com.sra.repository;

import com.sra.model.Reservation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Transactional
@Repository
public class ReservationsRepository extends DataRepository<Reservation> {


    @Autowired
    public ReservationsRepository(EntityManager em) {
        super(em);
    }

    public boolean isEmpty() {
        Query query = super.getEntityManager().createQuery("SELECT COUNT(r) FROM Reservation r");
        return  super.isEmpty(query);
    }

    public List<Reservation> getData() {
        TypedQuery<Reservation> query = super.getEntityManager().createQuery("SELECT r FROM Reservation r", Reservation.class);
        return query.getResultList();
    }

    public void removeReservation (Reservation reservation)  {
        String stringQuery = "DELETE FROM Reservation r WHERE r.id = :id";
        Query query = super.getEntityManager().createQuery(stringQuery);
        query.setParameter("id", reservation.getId());
        query.executeUpdate();
    }

    public void addReservation(Reservation reservation) {
        super.addEntity(reservation);
    }


}
