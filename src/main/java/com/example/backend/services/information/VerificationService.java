package com.example.backend.services.information;

import com.example.backend.models.entity.Verification;
import com.example.backend.repository.VerificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VerificationService {

    private final VerificationRepository verificationRepository;
    public void saveVerificationToken(Verification token){
        verificationRepository.save(token);
    }

    public Optional<Verification> getToken(String token){
        return verificationRepository.findByToken(token);
    }

    public int setConfirmedAt(String token){
        return verificationRepository.updateConfirmedAy(token , LocalDateTime.now());
    }
}
