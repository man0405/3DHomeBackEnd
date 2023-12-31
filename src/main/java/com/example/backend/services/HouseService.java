package com.example.backend.services;

import com.example.backend.models.entity.House;

import java.util.List;
import java.util.UUID;

public interface HouseService {

	List<House> findAll();
	
	House findById(UUID theId);
	
	UUID save(House theHouse);
	
	void deleteById(UUID theId);
	
}
