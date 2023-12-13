package com.example.backend.models.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id;

    private String name;

    private String type;

    @Lob
    @Column(name = "imageData", length = 1000)
    private byte[] imageData;

    private String getImage;
}
