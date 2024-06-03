package com.example.backend.services;

import com.example.backend.dto.FurnitureResponse;
import com.example.backend.dto.HouseResponse;
import com.example.backend.models.entity.House;
import org.springframework.data.domain.Page;

import java.util.List;


public interface VisitService {

    void saveHouse(int customerId, int houseId);
    void saveFurniture(int customerId, int furnitureId);

    boolean updateFav(int customerId, int houseId);

    Boolean updateFavF(int customerId, int furniture);

    Page<HouseResponse> likedHouse(int offset, int pageSet, int customerId);
    Page<FurnitureResponse> likedFurniture(int offset, int pageSet, int customerId);

    Page<House> findSeenHouse(int offset, int pageSet, int customerId);

    void updatePriority(int customerId , int houseId);



    List<Integer> visitPerWeek(int theHouseId);

    List<Integer> totalVisitPerMonth(int ownerId);
}
