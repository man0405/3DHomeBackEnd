package com.example.backend.repository;

import com.example.backend.models.entity.House;
import com.example.backend.models.entity.Owner;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OwnerRepoCustomImpl implements OwnerRepoCustom {
    private final EntityManager entityManager;
    @Autowired
    public OwnerRepoCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Owner getOwnerAlongWithHouses(int theId) {
        TypedQuery<Owner> query = entityManager.createQuery("SELECT o from Owner o JOIN FETCH o.houses where o.id = :data", Owner.class);
        query.setParameter("data", theId);
        return query.getSingleResult();
    }


    @Override
    public void updateOwner(Owner theOwner) {
        entityManager.merge(theOwner);
    }

}
