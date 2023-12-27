package com.example.backend.repository;

import com.example.backend.models.entity.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HouseRepo extends JpaRepository<House,Integer>, HouseRepoCustom {

    List<House> findByIdIn(List<Integer> id);
    Page<House> findByIdIn(List<Integer> id, Pageable pageable);
}

