package com.example.backend.repository;

import com.example.backend.models.entity.House;

public interface HouseRepoCustom {
    House findBy_Id(int id);
}
