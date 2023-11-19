
package com.example.backend.models.entity;

        import jakarta.persistence.*;
        import lombok.*;

        import java.util.ArrayList;
        import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "customer")
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String phone;


    @JoinColumn(name = "user_id")
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private User user;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "Visit",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "house_id")
    )
    private List<House> houses;



    public void addHouse(House theHouse){
        if(houses == null)
            houses = new ArrayList<>();
        houses.add(theHouse);
    }


}
