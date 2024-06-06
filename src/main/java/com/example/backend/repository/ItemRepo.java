package com.example.backend.repository;

import com.example.backend.models.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ItemRepo extends JpaRepository<Item, Long> {

    Optional<Item> findByCustomerIdAndFurnitureId(Long customerId, Long furnitureId);
}
