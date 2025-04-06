package com.repository;

import com.model.TypeOfSpace;
import com.model.Space;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpacesDataRepository extends DataRepository<Space> {

    @Autowired
    public SpacesDataRepository(EntityManager em) {
        super(em);
    }

    public boolean isEmpty() {
        TypedQuery<Long> query = super.getEntityManager().createQuery("SELECT COUNT(s) FROM Space s", Long.class);
        return  super.isEmpty(query);
    }

    public List<Space> getData() {
        TypedQuery<Space> query = super.getEntityManager().createQuery("SELECT s FROM Space s", Space.class);
        return query.getResultList();
    }

    public boolean exists(Space space) {
        for (Space existingSpace : this.getData()) {
            if (space.getName().equalsIgnoreCase(existingSpace.getName())) {
                return true;
            }
        }
        return false;
    }

    @Transactional
    public void syncSpaceWithRepository(Space updatedSpace) {
        Space existingSpace = super.getEntityManager().find(Space.class, updatedSpace.getId());
        if (existingSpace != null) {
            existingSpace.setName(updatedSpace.getName());
            existingSpace.setPrice(updatedSpace.getPrice());
            existingSpace.setType(updatedSpace.getTypeOfSpace());
        } else {
            // Handle the case where the space doesn't exist (e.g., throw an exception)
            throw new EntityNotFoundException("Space with ID " + updatedSpace.getId() + " not found.");
        }
    }

    @Transactional
    public void addSpace (Space space) {
        super.addEntity(space);
    }

    @Transactional
    public void removeSpace(Space space) {
        String reservationQuery = "DELETE FROM Reservation r WHERE r.space.id = :spaceId";
        Query reservationDeleteQuery = super.getEntityManager().createQuery(reservationQuery);
        reservationDeleteQuery.setParameter("spaceId", space.getId());
        reservationDeleteQuery.executeUpdate();

        String spaceQuery = "DELETE FROM Space s WHERE s.id = :id";
        Query spaceDeleteQuery = super.getEntityManager().createQuery(spaceQuery);
        spaceDeleteQuery.setParameter("id", space.getId());
        spaceDeleteQuery.executeUpdate();
    }

    public Space getSpaceById (int spaceId) {
       return super.getEntityManager().find(Space.class, spaceId);
    }

    public void updateSpaceName(int spaceId, String newName) {
        Space existingSpace = super.getEntityManager().find (Space.class, spaceId);
        existingSpace.setName(newName);
    }

    public void updateSpacePrice(int spaceId, int newPrice) {
            Space existingSpace = super.getEntityManager().find(Space.class, spaceId);
            existingSpace.setPrice(newPrice);
    }

    public void updateSpaceType(int spaceId, TypeOfSpace typeOfSpace) {
        Space existingSpace = super.getEntityManager().find (Space.class, spaceId);
        existingSpace.setType(typeOfSpace);
    }

}
