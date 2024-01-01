package com.example.backend.services;


import com.example.backend.dto.VisitCustomerRes;
import com.example.backend.models.entity.House;
import com.example.backend.models.entity.Owner;

import java.util.List;

public interface OwnerService {

	List<Owner> findAll();
	
	Owner findById(int theId);

	Owner findByUser_Id(Long userId);
	

	Owner getOwnerAlongWithHouses(int theId);

	Owner save(Owner theOwner);

	void update(Owner theOwner);
	
	void deleteById(int theId);

	int addHouse(int OnwerId, House House);

	List<Integer> statistics(int ownerId);

	List<VisitCustomerRes> findVisitCustomerInfo(int ownerId);
}

