package com.example.backend.repository;

import com.example.backend.dto.HouseResponse;
import com.example.backend.models.entity.House;
import com.example.backend.models.entity.Visit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;


@Repository
@Transactional(readOnly = true)
public interface VisitRepository extends JpaRepository<Visit, Long> {



    @Transactional
    @Modifying
    @Query(value = "insert into visit (house_id, customer_id ,day_visited, time_visited, favorite) values(?2, ?1, ?3, ?4, ?5)", nativeQuery = true)
    void save(int customerId, int houseId, LocalDate dayVisited, LocalTime timeVisited, Boolean fav);

    @Transactional
    @Modifying
    @Query(value = "insert into visit (furniture_id, customer_id ,day_visited, time_visited, favorite) values(?5, ?1, ?2, ?3, ?4)", nativeQuery = true)
    void save(int customerId, LocalDate dayVisited, LocalTime timeVisited, Boolean fav, int furnitureId);

    @Query(value = "select id from Visit where house_id = ?2 and customer_id=?1", nativeQuery = true)
    Integer findHouseExisting(int customerId, int houseId);

    @Query(value = "select id from Visit where furniture_id = ?2 and customer_id=?1", nativeQuery = true)
    Integer findFurnitureExisting(int customerId, int furniture);


    @Transactional
    @Modifying
    @Query("update Visit v set v.priority = true where v.customer.id= ?1 and v.house.Id = ?2")
    void updatePriority(int customerId, int houseId);


    Optional<Visit> findVisitByCustomer_IdAndHouse_Id(int customerId, int houseId);

    Optional<Visit> findVisitByCustomer_IdAndFurniture_Id(int customerId, int furnitureId);


//    @Query(value = "select house_id from Visit  where customer_id = ?1 order by id desc" , nativeQuery = true)
//    List<Integer> findAllHouseIdsByCustomerId(int customerId);


//    @Query(value = "SELECT house_id FROM Visit WHERE customer_id = ?1 ORDER BY id DESC", nativeQuery = true)
//    List<Integer> findAllHouseIdsByCustomerId(int customerId);
//

    @Query(value = "select v.house from Visit v where v.customer.id = ?1 order by v.dayVisited, v.timeVisited desc")
    Page<House> findAllHouseIdsByCustomerId(int customerId, Pageable pageable);

//    int id,
//    String name,
//    String image,
//    String district,
//    double price,
//    boolean fav



    @Query(value = "select new com.example.backend.dto.HouseResponse(v.house, v.favorite) from Visit v where v.favorite = TRUE and v.customer.id = ?1  order by v.dayVisited, v.timeVisited desc")
    Page<HouseResponse> findLikedHouse(int customerId, Pageable pageable);

    @Query(value = "select COUNT(v) from Visit v where v.house.Id = ?1 and MONTH (v.dayVisited) = ?2")
    Integer visitPerWeek(int HouseId, int month);

    @Query(value = "select COUNT(v) from Visit v where v.house.owner.Id = ?1 and MONTH (v.dayVisited) = ?2")
    Integer totalVisitPerMonth(int ownerId, int month);
}
