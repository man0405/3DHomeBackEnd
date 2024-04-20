package com.example.backend.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(
//            mappedBy = "invoice",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
            ,orphanRemoval = true
    )
    @JsonIgnore
    private List<Cart> cartList;

    private LocalDate dayVisited;

    private LocalTime timeVisited;

    private Double price;

    public void addCart(Cart theCart){
        if(cartList == null)
            cartList = new ArrayList<>();
        cartList.add(theCart);
    }


}
