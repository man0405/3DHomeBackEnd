package com.example.backend.models.entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Verification {

    private  static final int EXPIRATION = 24*60;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name="user_id")
    private User user;


    @Column(nullable = false)
    private  String token;

    @Column(nullable = false)
    private LocalDateTime createAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private  LocalDateTime confirmedAt;


    public Verification(String  token, LocalDateTime createAt, LocalDateTime expiresAt, User user) {
        this.user = user;
        this.createAt = createAt;
        this.expiresAt = expiresAt;
        this.token = token;

    }
}
