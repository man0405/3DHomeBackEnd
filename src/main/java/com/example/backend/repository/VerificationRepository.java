package com.example.backend.repository;


import com.example.backend.models.entity.Verification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


@Repository
@Transactional(readOnly = true)
public interface VerificationRepository extends JpaRepository<Verification , Long> {

    Optional<Verification> findByToken(String token);

    @Transactional
    @Modifying
    @Query("update Verification  v " +
            "set v.confirmedAt = ?2 " +
            "where v.token = ?1")
    int updateConfirmedAy(String token, LocalDateTime confirmedAt);
}
