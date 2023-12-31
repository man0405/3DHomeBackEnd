package com.example.backend.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "house")
public class House {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(name = "id")
    private UUID Id;
    @Column (name = "price")
    private double price;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "information_id")
    private Information information;
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
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
    private List<Image> images;



    public House(double price, long number, String street, String province, String city, String country, double landSize, int numberOfFloor, FacingDirection direction, int bedrooms, int toilets) {
        this.price = price;
        information = new Information();
        this.information.setNumber(number);
        this.information.setStreet(street);
        this.information.setProvince(province);
        this.information.setCity(city);
        this.information.setCountry(country);
        this.information.setLandSize(landSize);
        this.information.setNumberOfFloor(numberOfFloor);
        this.information.setDirection(direction);
        this.information.setBedrooms(bedrooms);
        this.information.setToilets(toilets);
    }

    public void addCustomer(Customer theCustomer){
        if(customers == null)
            customers = new ArrayList<>();
        customers.add(theCustomer);
    }

    public void addImage(Image theImage){
        if(images == null)
            images = new ArrayList<>();
        images.add(theImage);
    }
}
