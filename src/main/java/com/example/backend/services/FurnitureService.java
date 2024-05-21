package com.example.backend.services;

import com.example.backend.dto.FurnitureFilter;
import com.example.backend.dto.FurnitureResponse;
import com.example.backend.models.entity.Furniture;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FurnitureService {

	List<Furniture> findAll();

	List<Furniture> findFurnituresWithSorting(String field);

	Furniture findById(Long theId);
	
	Furniture save(Furniture theFurniture);
	
	void deleteById(int theId);

	Page<FurnitureResponse> searchFurniture(int customerId,String name, String price, String warranty, String material , int offset, int pageSet);


	FurnitureFilter getFilter();
	Page<FurnitureResponse> findFurnituresWithPaginationAndSort(int offset, int pageSet, String field, int customerId);

}
