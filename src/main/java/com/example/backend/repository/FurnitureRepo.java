package com.example.backend.repository;

import com.example.backend.models.entity.Furniture;
import com.example.backend.models.entity.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FurnitureRepo extends JpaRepository<Furniture, Long> {

    @Query(value = "SELECT h FROM Furniture h WHERE LOWER(h.name) like LOWER(CONCAT('%',:name,'%') ) ")
    Page<Furniture> searchFurnitureByName(String name, Pageable pageable);

}
