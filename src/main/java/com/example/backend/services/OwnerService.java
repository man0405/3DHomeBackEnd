package com.example.backend.services;


import com.example.backend.models.entity.House;
import com.example.backend.models.entity.Owner;

import java.util.List;

public interface OwnerService {

	List<Owner> findAll();
	
	Owner findById(int theId);

	Owner getOwnerAlongWithHouses(int theId);

	Owner save(Owner theOwner);

	void update(Owner theOwner);
	
	void deleteById(int theId);

	House addHouse(int OnwerId, int HouseId);
}
