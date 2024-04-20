package com.example.backend.services;

import com.example.backend.models.entity.Furniture;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FurnitureService {

	List<Furniture> findAll();

	List<Furniture> findFurnituresWithSorting(String field);

	Furniture findById(Long theId);
	
	Furniture save(Furniture theFurniture);
	
	void deleteById(int theId);

//	Page<Furniture> findOwnerFurnitures(int id,int offset , int pageSet, String field);

	Page<Furniture> findFurnituresWithPaginationAndSort(int offset, int pageSet, String field);

}
