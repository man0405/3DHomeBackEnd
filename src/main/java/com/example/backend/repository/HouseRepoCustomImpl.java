package com.example.backend.repository;

import com.example.backend.models.entity.House;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class HouseRepoCustomImpl implements HouseRepoCustom{
    @Autowired
    private EntityManager entityManager;


    @Override
    public House findBy_Id(UUID id) {
        return entityManager.find(House.class, id);
    }
}
