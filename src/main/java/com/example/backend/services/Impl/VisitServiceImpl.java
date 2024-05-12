package com.example.backend.services.Impl;

import com.example.backend.models.entity.House;
import com.example.backend.models.entity.Visit;
import com.example.backend.repository.HouseRepo;
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
    public void save(int customerId, int houseId) {
        if(checkExisting(customerId,houseId)){
            return;
        }
        visitRepository.save(customerId, houseId, LocalDate.now(), LocalTime.now(), false);
    }

    @Override
    public void updateFav(int customerId, int houseId) {
        Optional<Visit> v = visitRepository.findVisitByCustomer_IdAndHouse_Id(customerId, houseId);
        if(v.isPresent()){
            v.get().setFavorite(!v.get().getFavorite());
            visitRepository.save(v.get());
        }else{
//            visitRepository
        }
    }

    @Override
    public Page<House> likedHouse(int offset, int pageSet, int customerId) {
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


    private boolean checkExisting(int customerId, int houseId){
        Integer id = visitRepository.findExisting(customerId, houseId);
        return id != null;
    }
}
