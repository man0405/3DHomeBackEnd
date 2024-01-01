package com.example.backend.repository;

import com.example.backend.models.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FileDataRepo extends JpaRepository<FileData, UUID> {
    Optional<FileData>  findById(UUID theId);
}
