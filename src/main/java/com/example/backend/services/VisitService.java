package com.example.backend.services;

import com.example.backend.models.entity.House;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VisitService {

    void save(int customerId, int houseId);

    Page<House> findSeenHouse(int offset, int pageSet, int customerId);

}
