package com.example.backend.repository;

import com.example.backend.models.entity.House;

import java.util.UUID;

public interface HouseRepoCustom {
    House findBy_Id(UUID id);
}
