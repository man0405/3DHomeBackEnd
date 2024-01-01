package com.example.backend.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "files")
public class FileData {

    @Id
    private UUID Id;

    private String type;

    @Column(length = 1024)
    private String filePath;

    private String getPath;

    private String src;

    private Boolean main;
}
