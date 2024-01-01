package com.example.backend.repository;

import com.example.backend.models.entity.House;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HouseRepoCustomImpl implements HouseRepoCustom{
    @Autowired
    private EntityManager entityManager;


    @Override
    public House findBy_Id(int id) {
        return entityManager.find(House.class, id);
    }
}
