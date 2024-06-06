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

    Optional<Cart> findCartByCustomerIdAndFurnitureId(int customerId, int furnitureId);

    @Query("select c from Cart  c where c.customer.id =?1  and c.furniture.id = ?2")
    Optional<Cart> findCart(int customerId, int furnitureId);

    @Query(value = "SELECT c FROM Cart c where c.customer.id = ?1")
    List<Cart> findByCustomer_IdAAndPaid(Long customerId);

    @Query(value = "select c from Cart  c where c.customer.id = ?1")
    List<Cart> findAllByCustomer_Id(Long customerId);

}
