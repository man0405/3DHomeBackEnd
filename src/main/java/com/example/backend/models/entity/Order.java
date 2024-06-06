package com.example.backend.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "order_")
public class Order  {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    @JsonIgnore
    private List<Item> items;

    private String status;

}
