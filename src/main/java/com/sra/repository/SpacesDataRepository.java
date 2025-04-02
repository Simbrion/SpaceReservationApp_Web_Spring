package com.sra.repository;

import com.sra.model.TypeOfSpace;
import com.sra.model.Space;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public class SpacesDataRepository extends DataRepository<Space> {

    @Autowired
    public SpacesDataRepository(EntityManager em) {
        super(em);
    }

    public boolean isEmpty() {
        Query query = super.getEntityManager().createQuery("SELECT COUNT(s) FROM Space s");
        return  super.isEmpty(query);
    }


    public List<Space> getData() {
        TypedQuery<Space> query = super.getEntityManager().createQuery("SELECT s FROM Space s", Space.class);
        return query.getResultList();
    }

    public void addSpace (Space space) {
        super.addEntity(space);
    }

    public void removeSpace (Space space) {
        String stringQuery = "DELETE FROM Space s WHERE s.id = :id";
        Query query = super.getEntityManager().createQuery(stringQuery);
        query.setParameter("id", space.getId());
        query.executeUpdate();
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
