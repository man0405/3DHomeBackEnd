package com.example.backend.repository;

import com.example.backend.models.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

//    @Query(value = "select c from Cart c where furniture_id = ?2 and customer_id=?1", nativeQuery = true)
//    Optional<Cart> findExisting(int customerId, int furnitureId);

//    Optional<Cart> findCartByCustomerIdAndFurnitureId(int customerId, int furnitureId);

}
