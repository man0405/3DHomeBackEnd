package com.example.backend.repository;

import com.example.backend.models.entity.Customer;
import com.example.backend.models.entity.House;
import com.example.backend.models.entity.Visit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
@Transactional(readOnly = true)
public interface VisitRepository extends JpaRepository<Visit, Long> {



    @Transactional
    @Modifying
    @Query(value = "insert into visit (house_id, customer_id , visited_at) values(?2, ?1, ?3)", nativeQuery = true)
    void save(int customerId, int houseId, LocalDateTime date);

    @Query(value = "select id from Visit where house_id = ?2 and customer_id=?1", nativeQuery = true)
    Integer findExisting(int customerId, int houseId);


    @Transactional
    @Modifying
    @Query("update Visit v set v.priority = true where v.customer.id= ?1 and v.house.Id = ?2")
    void updatePriority(int customerId, int houseId);

    Optional<Visit> findVisitByCustomer_IdAndHouse_Id(int customerId, int houseId);


//    @Query(value = "select house_id from Visit  where customer_id = ?1 order by id desc" , nativeQuery = true)
//    List<Integer> findAllHouseIdsByCustomerId(int customerId);


//    @Query(value = "SELECT house_id FROM Visit WHERE customer_id = ?1 ORDER BY id DESC", nativeQuery = true)
//    List<Integer> findAllHouseIdsByCustomerId(int customerId);
//

    @Query(value = "select v.house from Visit v where v.customer.id = ?1 order by v.VisitedAt desc")
    Page<House> findAllHouseIdsByCustomerId(int customerId, Pageable pageable);



}
