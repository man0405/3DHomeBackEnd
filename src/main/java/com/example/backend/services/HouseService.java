package com.example.backend.services;

import com.example.backend.models.entity.House;

import java.util.List;

public interface HouseService {

	List<House> findAll();
	
	House findById(int theId);
	
	void save(House theHouse);
	
	void deleteById(int theId);
	
}
