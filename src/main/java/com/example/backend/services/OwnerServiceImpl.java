package com.example.backend.services.Impl;

import com.example.backend.models.entity.House;
import com.example.backend.repository.HouseRepo;
import com.example.backend.repository.OwnerRepo;
import com.example.backend.models.entity.Owner;
import com.example.backend.services.OwnerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {

	private OwnerRepo ownerRepo;
	@Autowired
	private HouseRepo houseRepo;

	@Autowired
	public OwnerServiceImpl(OwnerRepo theOwnerRepo) {
		ownerRepo = theOwnerRepo;
	}
	
	@Override
	public List<Owner> findAll() {
		return ownerRepo.findAll();
	}

	@Override
	public Owner findById(int theId) {
		Optional<Owner> result = ownerRepo.findById(theId);

		Owner theOwner = null;

		if (result.isPresent()) {
			theOwner = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find employee id - " + theId);
		}

		return theOwner;
	}

	@Override
	public Owner getOwnerAlongWithHouses(int theId) {
		Owner result = ownerRepo.getOwnerAlongWithHouses(theId);

//		Owner theOwner = null;

		if (result == null)
			throw new RuntimeException("Did not find owner id - " + theId);
		return result;
	}



	@Override
	@Transactional
	public Owner save(Owner theOwner) {
		return ownerRepo.save(theOwner);
	}

	@Override
	@Transactional
	public void update(Owner theOwner) {
		try {
			ownerRepo.updateOwner(theOwner);
		}catch (Exception e){
			e.printStackTrace();
		}
	}



	@Override
	@Transactional
	public void deleteById(int theId) {
		ownerRepo.deleteById(theId);
	}

	@Override
	@Transactional
	public House addHouse(int OwnerId, int houseId) {
		Owner theOwner = this.findById(OwnerId);
		House theHouse = houseRepo.findBy_Id(houseId);
		theOwner.addHouse(theHouse);
		update(theOwner);
		return theHouse;
	}
}






