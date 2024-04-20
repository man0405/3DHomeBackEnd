package com.example.backend.repository;

import com.example.backend.models.entity.Furniture;
import com.example.backend.models.entity.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FurnitureRepo extends JpaRepository<Furniture, Long> {


}
