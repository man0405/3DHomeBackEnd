package com.example.backend.repository;

import com.example.backend.models.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepo extends JpaRepository<Image, Long> {
    Optional<Image> findByName(String name);

}
