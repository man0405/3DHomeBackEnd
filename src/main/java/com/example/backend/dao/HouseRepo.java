package com.example.backend.dao;

import com.example.backend.models.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepo extends JpaRepository<House,Integer> {

}
