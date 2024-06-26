package com.example.backend.models.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "furniture")
public class Furniture {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String color;

    private String material;

    private Double price;

    private String description;

    private int warranty;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "furniture_id")
    private List<FileData> images;

    public void addImage(FileData theImage){
        if(images == null)
            images = new ArrayList<>();
        images.add(theImage);
    }
}
