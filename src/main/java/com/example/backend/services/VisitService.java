package com.example.backend.services;

import com.example.backend.models.entity.House;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;


public interface VisitService {

    void save(int customerId, int houseId);

    Page<House> findSeenHouse(int offset, int pageSet, int customerId);

    void updatePriority(int customerId , int houseId);

    List<Integer> visitPerWeek(int theHouseId);

    List<Integer> totalVisitPerMonth(int ownerId);
}
