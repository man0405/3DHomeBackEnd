package com.example.backend.models.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "item")
public class Item {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    private Furniture furnitureId;

    @OneToOne
    private Customer customerId;

    private int quantity;

    private LocalDate localDate;
}
