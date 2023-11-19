package com.example.backend.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "House")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;
    @Column(name = "address")
    private String address;
    @Column(name = "information")
    private String information;
//    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Owner owner;
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinTable(
            name = "Visit",
            joinColumns = @JoinColumn(name = "house_id"),
            inverseJoinColumns=@JoinColumn(name = "customer_id")
    )
    private List<Customer> customers;
    public House() {
    }

    public House(String address, String information) {
        this.address = address;
        this.information = information;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void addCustomer(Customer theCustomer){
        if(customers == null)
            customers = new ArrayList<>();
        customers.add(theCustomer);
    }

    @Override
    public String toString() {
        return "House{" +
                "Id=" + Id +
                ", address='" + address + '\'' +
                ", information='" + information + '\'' +
                '}';
    }
}
