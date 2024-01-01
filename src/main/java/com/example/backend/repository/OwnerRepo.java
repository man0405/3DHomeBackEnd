package com.example.backend.repository;

import com.example.backend.dto.VisitCustomerRes;
import com.example.backend.models.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OwnerRepo extends JpaRepository<Owner, Integer>, OwnerRepoCustom {


    @Query("select o from Owner o  where o.user.id = ?1 ")
    Owner findByUser_Id(Long userId);



    @Query(value = "select count(*)  from owners o" +
            "    join public.house h on o.id = h.owner_id" +
            "    join public.visit v on h.id = v.house_id" +
            "                     where o.id=?1", nativeQuery = true)
    int viewProject(int ownerId);

    @Query(value = "select count(*)  from owners o" +
            "    join public.house h on o.id = h.owner_id" +
            "    join public.visit v on h.id = v.house_id" +
            "    where o.id=?1 and v.priority = true", nativeQuery = true)
    int leaveInformation(int ownerId);


    @Query(value = "select count(*)  from owners o" +
            " join public.house h on o.id = h.owner_id where o.id = ?1" , nativeQuery = true)
    int totalProject(int ownerId);


    @Query(value = "select v.priority, h.name, c.first_name,c.last_name, u.email , c.phone from visit v" +
            "    join public.house h on h.id = v.house_id" +
            "    join customer c on c.id = v.customer_id" +
            "    join public._user u on u.id = c.user_id" +
            "    where h.owner_id = 51" , nativeQuery = true)
    List<Object[]> VisitCustomerInfo(int ownerId);
}
