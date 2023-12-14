package com.example.backend.services;

import com.example.backend.dto.HouseResponse;
import com.example.backend.models.entity.House;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HouseService {

	List<House> findAll();

	List<House> findHousesWithSorting(String field);

	House findById(int theId);
	
	House save(House theHouse);
	
	void deleteById(int theId);

	Page<HouseResponse> findHousesWithPaginationAndSort(int offset, int pageSet, String field);

}
