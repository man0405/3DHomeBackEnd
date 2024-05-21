package com.example.backend.repository;

import com.example.backend.dto.FurnitureResponse;
import com.example.backend.models.entity.Furniture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FurnitureRepo extends JpaRepository<Furniture, Long> {


    @Query("SELECT new com.example.backend.dto.FurnitureResponse(f, coalesce(v.favorite, false) ) " +
            "FROM Furniture f " +
            "LEFT JOIN Visit v ON f.id = v.furniture.id AND v.customer.id = ?1")
    Page<FurnitureResponse> findFurnitureWithPaginationAndSort(int customerId, Pageable pageable);

    @Query(value = "SELECT h FROM Furniture h WHERE LOWER(h.name) like LOWER(CONCAT('%',:name,'%') ) ")
    Page<Furniture> searchFurnitureByName(String name, Pageable pageable);


    @Query("select new com.example.backend.dto.FurnitureResponse(f,coalesce(v.favorite, false ) ) from Furniture f  " +
            "LEFT JOIN Visit v ON f.id = v.furniture.id AND v.customer.id = ?1 " +
            "where lower(f.name) like lower(concat('%',?2,'%')) " + "and (?3 is null or f.price between ?4 and ?5) "
            +" and (?6 is null or f.warranty = ?6) "
            + "and (?7 is null or f.material = ?7)"
    )
    Page<FurnitureResponse> searchFurnitureByFilter(int idCustomer, String name, String price, int minPrice, int maxPrice,String warranty, String material, Pageable pageable);


    @Query("select concat((floor(h.price / 200000) * 200000 ), '-', (floor (h.price/ 200000)*200000 + 199999)), count (*) " +
            "from Furniture h "+
            "group by concat((floor(h.price / 200000) * 200000 ), '-', (floor (h.price/ 200000)*200000 + 199999)) " +
            "order by concat((floor(h.price / 200000) * 200000 ), '-', (floor (h.price/ 200000)*200000 + 199999)) asc")
    List<Object[]> priceRange();

    @Query("select  distinct f.warranty  from Furniture f " +
            "order by  f.warranty asc")
    List<String> warrantyRange();

    @Query("select  distinct f.material  from Furniture f " +
            "order by  f.material asc")
    List<String> materialRange();
}
