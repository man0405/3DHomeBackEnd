package com.example.backend.services.Impl;

import com.example.backend.dto.APIResponse;
import com.example.backend.dto.CheckResponse;
import com.example.backend.dto.FurnitureResponse;
import com.example.backend.dto.HouseResponse;
import com.example.backend.models.entity.House;
import com.example.backend.models.entity.Visit;
import com.example.backend.repository.VisitRepository;
import com.example.backend.services.VisitService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


@Service
@AllArgsConstructor
public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;


    @Override
    public void saveHouse(int customerId, int houseId) {
        if(checkHouseExisting(customerId,houseId)){
            return;
        }
        visitRepository.save(customerId, houseId, LocalDate.now(), LocalTime.now(), false);
    }

    @Override
    public void saveFurniture(int customerId, int furnitureId) {
        if(checkFurnitureExisting(customerId,furnitureId)){
            return;
        }
        visitRepository.save(customerId, LocalDate.now(), LocalTime.now(), false, furnitureId);
    }

    @Override
    public boolean updateFav(int customerId, int houseId) {
        System.out.println("customerId, houseId: " + customerId + ' ' + houseId);
        boolean f;
        Optional<Visit> v = visitRepository.findVisitByCustomer_IdAndHouse_Id(customerId, houseId);
        if(v.isPresent()){
            f = !v.get().getFavorite();
            v.get().setFavorite(!v.get().getFavorite());
            visitRepository.save(v.get());
        }else{
            f = true;
            visitRepository.save(customerId, houseId, LocalDate.now(), LocalTime.now(), true);
        }
        return f;
    }

    @Override
    public Boolean updateFavF(int customerId, int furniture) {
        System.out.println("customerId, houseId: " + customerId + ' ' + furniture);
        boolean f;
        Optional<Visit> v = visitRepository.findVisitByCustomer_IdAndFurniture_Id(customerId, furniture);
        if(v.isPresent()){
            f = !v.get().getFavorite();
            v.get().setFavorite(!v.get().getFavorite());
            visitRepository.save(v.get());
        }else{
            f = true;
            visitRepository.save(customerId, LocalDate.now(), LocalTime.now(), true, furniture);
        }
        return f;
    }

    @Override
    public Page<HouseResponse> likedHouse(int offset, int pageSet, int customerId) {
        return visitRepository.findLikedHouse(customerId, PageRequest.of(offset, pageSet));
    }


    @Override
    public Page<House> findSeenHouse(int offSet, int pageSet, int customerId) {
        return visitRepository.findAllHouseIdsByCustomerId(customerId, PageRequest.of(offSet, pageSet));
    }

    @Override
    public void updatePriority(int customerId, int houseId) {
        var visit = visitRepository.findVisitByCustomer_IdAndHouse_Id(customerId, houseId);
        if(visit.isPresent() && visit.get().getPriority()==null){
            visitRepository.updatePriority(customerId, houseId);
        }
    }

    @Override
    public List<Integer> visitPerWeek(int theHouseId) {
        List<Integer> integerList = new ArrayList<>();
        int currentMonth = LocalDate.now().getMonthValue();
        Integer integer = null;
        for(int i = 2; i >= 0; i--){
            if(currentMonth - i > 0)
               integer  = visitRepository.visitPerWeek(theHouseId, currentMonth - i);
            else
                integer = visitRepository.visitPerWeek(theHouseId, currentMonth - i + 12);
            integerList.add(integer);
        }
            return integerList;
    }

    @Override
    public List<Integer> totalVisitPerMonth(int ownerId){
        List<Integer> integerList = new ArrayList<>();
        int currentMonth = LocalDate.now().getMonthValue();
        Integer integer = null;
        for(int i = 11; i >= 0; i--){
            if(currentMonth - i > 0)
                integer = visitRepository.totalVisitPerMonth(ownerId, currentMonth - i);
            else
                integer = visitRepository.totalVisitPerMonth(ownerId, currentMonth - i + 12);
            integerList.add(integer);
        }
        return integerList;
    }


    private boolean checkHouseExisting(int customerId, int houseId){
        Integer id = visitRepository.findHouseExisting(customerId, houseId);
        return id != null;
    }

    private boolean checkFurnitureExisting(int customerId, int houseId){
        Integer id = visitRepository.findFurnitureExisting(customerId, houseId);
        return id != null;
    }

    @Override
    public Page<FurnitureResponse> likedFurniture(int offset, int pageSet, int customerId) {
        return visitRepository.findLikedFurniture(customerId, PageRequest.of(offset, pageSet));
    }
}
