package com.example.backend.services.Impl;

import com.example.backend.models.entity.Information;
import com.example.backend.repository.HouseRepo;
import com.example.backend.models.entity.House;
import com.example.backend.repository.InformationRepo;
import com.example.backend.services.HouseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseServiceImpl implements HouseService {

	private HouseRepo houseRepo;
	private final InformationRepo informationRepo;

	@Autowired
	public HouseServiceImpl(HouseRepo theHouseRepo, InformationRepo informationRepo) {
		houseRepo = theHouseRepo;
		this.informationRepo = informationRepo;
	}
	
	@Override
	public List<House> findAll() {
		return houseRepo.findAll();
	}

	@Override
	public House findById(int theId) {
		Optional<House> result = houseRepo.findById(theId);
		
		House theHouse = null;
		
		if (result.isPresent()) {
			theHouse = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find owner id - " + theId);
		}
		return theHouse;
	}

	@Override
	@Transactional
	public int save(House theHouse) {
		houseRepo.save(theHouse);
		System.out.println(theHouse);
		return theHouse.getId();
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		houseRepo.deleteById(theId);
	}

}






