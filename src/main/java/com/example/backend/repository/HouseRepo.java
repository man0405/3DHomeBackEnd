package com.example.backend.repository;

import com.example.backend.dto.HouseResponse;
import com.example.backend.models.entity.House;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface HouseRepo extends JpaRepository<House,Integer>, HouseRepoCustom {


    @Query("SELECT new com.example.backend.dto.HouseResponse(h, coalesce(v.favorite, false) ) " +
            "FROM House h " +
            "LEFT JOIN Visit v ON h.Id = v.house.Id AND v.customer.id = ?1")
    Page<HouseResponse> findHousesWithPaginationAndSort(int customerId, Pageable pageable);

    Page<House> findHousesByOwner_Id(int id , Pageable pageable);

   // select  from House h where lower(h.name) like lower(concat('%',':name','%'))
//    @Query(value = "
//            select h from House h where lower(h.name) like lower(concat('%',?1,'%'))
//            ")

    @Query(value = "SELECT h FROM House h WHERE LOWER(h.name) like LOWER(CONCAT('%',:name,'%') ) ")
    Page<House> searchHouseByName(String name, Pageable pageable);
}

