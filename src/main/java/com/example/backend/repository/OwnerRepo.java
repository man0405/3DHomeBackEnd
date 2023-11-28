package com.example.backend.repository;

import com.example.backend.models.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepo extends JpaRepository<Owner, Integer>, OwnerRepoCustom {
}
