package com.example.backend.repository;

import com.example.backend.models.entity.Furniture;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FurnitureRepository extends JpaRepository<Furniture, Long> {

    Furniture findById(int furnitureId);
}
