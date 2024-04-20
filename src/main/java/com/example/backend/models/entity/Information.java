package com.example.backend.models.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "information")
public class Information {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private long number;

    @Column(name = "street")
    private String street;

    @Column(name = "district")
    private String district;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "land_size")
    private double landSize;

    @Column(name = "number_of_floor")
    private int numberOfFloor;

    @Column(name = "facing_direction")
    @Enumerated(EnumType.STRING)
    private FacingDirection direction;

    @Column(name = "bedrooms")
    private int bedrooms;

    @Column(name = "toilets")
    private int toilets;


    private Boolean heart;


    public Information(long number, String street, String district, String city, String country, double landSize, int numberOfFloor, FacingDirection direction, int bedrooms, int toilets, Boolean heart) {
        this.number = number;
        this.street = street;
        this.district = district;
        this.city = city;
        this.country = country;
        this.landSize = landSize;
        this.numberOfFloor = numberOfFloor;
        this.direction = direction;
        this.bedrooms = bedrooms;
        this.toilets = toilets;
        this.heart= heart;
    }
}
