package com.example.backend.repository;

import com.example.backend.models.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Long> {
}
