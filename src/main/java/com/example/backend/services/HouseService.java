package com.example.backend.services;

import com.example.backend.dto.FilterResponse;
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

	Page<House> findOwnerHouses(int id,int offset , int pageSet, String field);
	Page<HouseResponse> searchHouse(Long id, String name, String landSize, int minLandSize, int maxLandSize, int bedRoom, String price, int minPrice, int maxPrice , int offset, int pageSet);


	FilterResponse getFilter();
	Page<HouseResponse> findHousesWithPaginationAndSort(int offset, int pageSet, String field, Long customerId);

}
