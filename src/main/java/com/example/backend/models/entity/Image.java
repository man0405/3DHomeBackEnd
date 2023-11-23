package com.example.backend.models.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "photo")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id;

    private String name;

    private String type;

    @Lob
    @Column(name = "imageData", length = 1000)
    private byte[] imageData;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST})
    private House house;
}
