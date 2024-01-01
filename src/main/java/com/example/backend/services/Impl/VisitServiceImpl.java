package com.example.backend.services.Impl;

import com.example.backend.models.entity.House;
import com.example.backend.models.entity.Visit;
import com.example.backend.repository.HouseRepo;
import com.example.backend.repository.VisitRepository;
import com.example.backend.services.VisitService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.*;
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
        visitRepository.save(customerId, houseId, LocalDate.now(), LocalTime.now());
    }


//    @Override
//    public Page<House> findSeenHouse(int offSet, int pageSet, int customerId) {
//        List<Integer> houseIds= visitRepository.findAllHouseIdsByCustomerId(customerId);
//        PageRequest pageRequest = PageRequest.of(offSet, pageSet);
//        Page<House> houses = houseRepo.findByIdIn(houseIds,pageRequest);
//        return houses;
//    }

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
        for(int i = 2; i >= 0; i--){
            Integer integer = visitRepository.visitPerWeek(theHouseId, LocalDate.now().getMonthValue() - i);
            integerList.add(integer);
        }
            return integerList;
    }

    private boolean checkExisting(int customerId, int houseId){
        Integer id = visitRepository.findExisting(customerId, houseId);
        return id != null;
    }
}