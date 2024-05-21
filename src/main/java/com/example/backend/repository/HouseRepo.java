package com.example.backend.repository;

import com.example.backend.dto.HouseResponse;
import com.example.backend.models.entity.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface HouseRepo extends JpaRepository<House,Integer>, HouseRepoCustom {


    @Query("SELECT new com.example.backend.dto.HouseResponse(h, coalesce(v.favorite, false) ) " +
            "FROM House h " +
            "LEFT JOIN Visit v ON h.Id = v.house.Id AND v.customer.id = ?1")
    Page<HouseResponse> findHousesWithPaginationAndSort(int customerId, Pageable pageable);

    Page<House> findHousesByOwner_Id(int id , Pageable pageable);





    @Query("select concat((floor(h.information.landSize / 2000) * 2000 ), '-', (floor (h.information.landSize/ 2000)*2000 + 1999)) , " +
            "count (*)  " +
            "from House h  " +
            "group by concat((floor(h.information.landSize / 2000) * 2000 ), '-', (floor (h.information.landSize/ 2000)*2000 + 1999)) " +
            "order by concat((floor(h.information.landSize / 2000) * 2000 ), '-', (floor (h.information.landSize/ 2000)*2000 + 1999)) asc ")
    List<Object[]> landSizeRange() ;



    @Query("select concat((floor(h.price / 2000000) * 2000000 ), '-', (floor (h.price/ 2000000)*2000000 + 1999999)), count (*) " +
            "from House h "+
            "group by concat((floor(h.price / 2000000) * 2000000 ), '-', (floor (h.price/ 2000000)*2000000 + 1999999)) " +
            "order by concat((floor(h.price / 2000000) * 2000000 ), '-', (floor (h.price/ 2000000)*2000000 + 1999999)) asc")
    List<Object[]> priceRange();

    @Query("select distinct h.information.bedrooms from House h " +
            "order by h.information.bedrooms asc ")
    List<Long> bedroomRange();

    @Query("select new com.example.backend.dto.HouseResponse(h,coalesce(v.favorite, false ) ) from House h  " +
         "LEFT JOIN Visit v ON h.Id = v.house.Id AND v.customer.id = ?1 " +
            "where lower(h.name) like lower(concat('%',?2,'%')) "
            + "and (?3 is null or h.information.landSize between ?4 and ?5) "
            + "and (?6 = 0 or h.information.bedrooms = ?6) "
            + "and (?7 is null or  h.price between ?8 and ?9)"
    )
    Page<HouseResponse> searchHouseByFilter(Long idCustomer,String name, String landSize,
                                            int minLandSize,int maxLandSize,
                                            int bedRoom, String price, int minPrice,
                                            int maxPrice ,Pageable pageable);
}

