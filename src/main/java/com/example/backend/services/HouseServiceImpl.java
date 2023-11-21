package com.example.backend.services;

import com.example.backend.repository.HouseRepo;
import com.example.backend.models.entity.House;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseServiceImpl implements HouseService {

	private HouseRepo houseRepo;

	@Autowired
	public HouseServiceImpl(HouseRepo theHouseRepo) {
		houseRepo = theHouseRepo;
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
	public void save(House theHouse) {
		houseRepo.save(theHouse);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		houseRepo.deleteById(theId);
	}

}






