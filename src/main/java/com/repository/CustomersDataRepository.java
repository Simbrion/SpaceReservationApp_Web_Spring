package com.repository;

import com.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomersDataRepository extends DataRepository<Customer> {

    @Autowired
    public CustomersDataRepository(EntityManager em) {
        super(em);
    }

    public boolean isEmpty() {
        TypedQuery<Long> query = super.getEntityManager().createQuery("SELECT COUNT(c) FROM Customer c", Long.class);
        return query.getSingleResult() == 0;
    }

    public List<Customer> getData() {
        TypedQuery<Customer> query = super.getEntityManager().createQuery("SELECT c FROM Customer c", Customer.class);
        return query.getResultList();
    }

    @Transactional
    public void removeCustomer(Customer customer) {
        String reservationQuery = "DELETE FROM Reservation r WHERE r.customer.id = :customerId";
        Query reservationDeleteQuery = super.getEntityManager().createQuery(reservationQuery);
        reservationDeleteQuery.setParameter("customerId", customer.getId());
        reservationDeleteQuery.executeUpdate();

        Customer managedCustomer = super.getEntityManager().find(Customer.class, customer.getId());
        if (managedCustomer != null) {
            super.getEntityManager().remove(managedCustomer);
        }
    }

    @Transactional
    public void addCustomer(Customer customer) {
        super.addEntity(customer);
    }

    public Customer getCustomerById(int customerId) {
        return super.getEntityManager().find(Customer.class, customerId);
    }

    public Customer findByNameIgnoreCase(String name) {
        TypedQuery<Customer> query = super.getEntityManager().createQuery(
                "SELECT c FROM Customer c WHERE LOWER(c.name) = LOWER(:name)", Customer.class);
        query.setParameter("name", name);
        List<Customer> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }


}

