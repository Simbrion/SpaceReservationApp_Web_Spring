package com.sra.repository;

import com.sra.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public class CustomersDataRepository extends DataRepository<Customer> {

    @Autowired
    public CustomersDataRepository(EntityManager em) {
        super(em);
    }

    public boolean isEmpty() {
        Query query =  super.getEntityManager().createQuery("SELECT COUNT(c) FROM Customer c");
        return  super.isEmpty(query);
    }

    public List<Customer> getData() {
        TypedQuery<Customer> query = super.getEntityManager().createQuery("SELECT c FROM Customer c", Customer.class);
        return query.getResultList();
    }

    public void removeCustomer (Customer customer)  {
        String stringQuery = "DELETE FROM Customer c WHERE c.id = :id";
        Query query = super.getEntityManager().createQuery(stringQuery);
        query.setParameter("id", customer.getId());
        query.executeUpdate();

    }

    public void addCustomer(Customer customer) {
        super.addEntity(customer);
    }

}

