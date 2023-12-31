package com.example.backend.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "house")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;
    @Column (name = "price")
    private double price;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "information_id")
    private Information information;


    @ManyToOne(
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER
    )
    @JoinColumn(name = "owner_id")
    @JsonBackReference
    private Owner owner;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinTable(
            name = "Visit",
            joinColumns = @JoinColumn(name = "house_id"),
            inverseJoinColumns=@JoinColumn(name = "customer_id")
    )
    private List<Customer> customers;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "house_id")
    private List<FileData> images;

    private String src;
    private String name;

    @Column(length = 100000)
    private String description;

    public House(double price, long number, String street, String district, String city, String country, double landSize, int numberOfFloor, FacingDirection direction, int bedrooms, int toilets, String src ,String name, String description) {
        this.price = price;
        information = new Information();
        this.information.setNumber(number);
        this.information.setStreet(street);
        this.information.setDistrict(district);
        this.information.setCity(city);
        this.information.setCountry(country);
        this.information.setLandSize(landSize);
        this.information.setNumberOfFloor(numberOfFloor);
        this.information.setDirection(direction);
        this.information.setBedrooms(bedrooms);
        this.information.setToilets(toilets);
        this.src= src;
        this.name= name;
        this.description = description;
    }

    public void addCustomer(Customer theCustomer){
        if(customers == null)
            customers = new ArrayList<>();
        customers.add(theCustomer);
    }

    public void addImage(FileData theImage){
        if(images == null)
            images = new ArrayList<>();
        images.add(theImage);
    }
}
