package com.example.backend.services.Impl;

import com.example.backend.models.entity.House;
import com.example.backend.repository.HouseRepo;
import com.example.backend.repository.OwnerRepo;
import com.example.backend.models.entity.Owner;
import com.example.backend.services.OwnerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {

	private final OwnerRepo ownerRepo;
	private final HouseRepo houseRepo;

	@Autowired
	public OwnerServiceImpl(OwnerRepo theOwnerRepo, HouseRepo houseRepo) {
		ownerRepo = theOwnerRepo;
		this.houseRepo = houseRepo;
	}
	
	@Override
	public List<Owner> findAll() {
		return ownerRepo.findAll();
	}

	@Override
	public Owner findById(int theId) {
		Optional<Owner> result = ownerRepo.findById(theId);

		Owner theOwner;

		if (result.isPresent()) {
			theOwner = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find owner id - " + theId);
		}

		return theOwner;
	}

	@Override
	public Owner findByUser_Id(Long userId) {
		return ownerRepo.findByUser_Id(userId);
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
			System.out.println("hello");
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
	public int addHouse(int OwnerId, House theHouse) {
		Owner theOwner = this.findById(OwnerId);
		theHouse.setOwner(theOwner);
		theOwner.addHouse(theHouse);
		update(theOwner);
		return theHouse.getId();
	}

	@Override
	public List<Integer> statistics(int ownerId) {
		List<Integer> temp = new ArrayList<>();
		temp.add( ownerRepo.viewProject(ownerId));
		temp.add( ownerRepo.leaveInformation(ownerId));
		temp.add( ownerRepo.totalProject(ownerId));
		return temp;
	}
}






