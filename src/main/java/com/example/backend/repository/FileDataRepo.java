package com.example.backend.repository;

import com.example.backend.models.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileDataRepo extends JpaRepository<FileData, Integer> {
    Optional<FileData>  findByName(String fileName);
}
