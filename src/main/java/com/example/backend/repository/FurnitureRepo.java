package com.example.backend.repository;

import com.example.backend.dto.FurnitureResponse;
import com.example.backend.dto.HouseResponse;
import com.example.backend.models.entity.Furniture;
import com.example.backend.models.entity.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FurnitureRepo extends JpaRepository<Furniture, Long> {


    @Query("SELECT new com.example.backend.dto.FurnitureResponse(f, coalesce(v.favorite, false) ) " +
            "FROM Furniture f " +
            "LEFT JOIN Visit v ON f.id = v.furniture.id AND v.customer.id = ?1")
    Page<FurnitureResponse> findFurnitureWithPaginationAndSort(int customerId, Pageable pageable);

    @Query(value = "SELECT h FROM Furniture h WHERE LOWER(h.name) like LOWER(CONCAT('%',:name,'%') ) ")
    Page<Furniture> searchFurnitureByName(String name, Pageable pageable);


}
