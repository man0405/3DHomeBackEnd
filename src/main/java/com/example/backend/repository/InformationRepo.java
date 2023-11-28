package com.example.backend.repository;

import com.example.backend.models.entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepo extends JpaRepository<Information, Long> {
}
