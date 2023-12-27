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

import java.util.*;


@Service
@AllArgsConstructor
public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;
    private final HouseRepo houseRepo;


    @Override
    public void save(int customerId, int houseId) {
        if(checkExisting(customerId,houseId)){
            return;
        }
        visitRepository.save(customerId, houseId);
    }

    @Override
    public Page<House> findSeenHouse(int offSet, int pageSet, int customerId) {
        List<Integer> houseIds= visitRepository.findAllHouseIdsByCustomerId(customerId);

        PageRequest pageRequest = PageRequest.of(offSet, pageSet);

        Page<House> page = houseRepo.findByIdIn(houseIds,pageRequest);
        return page;

    }

    private boolean checkExisting(int customerId, int houseId){
        Integer id = visitRepository.findExisting(customerId, houseId);
        return id != null;
    }
}
