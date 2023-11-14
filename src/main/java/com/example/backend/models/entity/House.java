package com.example.backend.models.entity;

import jakarta.persistence.*;

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

    @Override
    public String toString() {
        return "House{" +
                "Id=" + Id +
                ", address='" + address + '\'' +
                ", information='" + information + '\'' +
                '}';
    }
    //add test comment
    //add test comment
}
