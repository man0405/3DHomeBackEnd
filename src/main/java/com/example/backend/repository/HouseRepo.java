package com.example.backend.repository;

import com.example.backend.models.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface HouseRepo extends JpaRepository<House, UUID>, HouseRepoCustom {

}

