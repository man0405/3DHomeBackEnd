package com.example.backend.services.Impl;

import com.example.backend.models.entity.Furniture;
import com.example.backend.repository.FurnitureRepo;
import com.example.backend.repository.InformationRepo;
import com.example.backend.services.FurnitureService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FurnitureServiceImpl implements FurnitureService {

	private final FurnitureRepo furnitureRepo;
	private final InformationRepo informationRepo;

	@Autowired
	public FurnitureServiceImpl(FurnitureRepo theFurnitureRepo, InformationRepo informationRepo) {
		furnitureRepo = theFurnitureRepo;
		this.informationRepo = informationRepo;
	}

	@Override
	public List<Furniture> findAll() {
		return furnitureRepo.findAll();
	}

	@Override
	public Furniture findById(Long theId) {
		Optional<Furniture> result = furnitureRepo.findById(Long.valueOf(theId));

		Furniture theFurniture = null;

		if (result.isPresent()) {
			theFurniture = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find owner id - " + theId);
		}
		return theFurniture;
	}

	@Override
	@Transactional
	public Furniture save(Furniture theFurniture) {
		return furnitureRepo.save(theFurniture);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		furnitureRepo.deleteById(Long.valueOf(theId));
	}

	@Override
	public Page<Furniture> searchFurniture(String name, int offset, int pageSet) {
		return furnitureRepo.searchFurnitureByName(name, PageRequest.of(offset - 1, pageSet));
	}


//	@Override
//	public Page<Furniture> findOwnerFurnitures(int id, int offset, int pageSet, String field) {
//		return furnitureRepo.findFurnituresByOwner_Id(id, PageRequest.of(offset,pageSet));
//	}


	public List<Furniture> findFurnituresWithSorting(String field){
		return furnitureRepo.findAll(Sort.by(Sort.Direction.ASC, field));
	}

	public Page<Furniture> findFurnituresWithPaginationAndSort(int offset, int pageSet, String field){
		return furnitureRepo.findAll(PageRequest.of(offset, pageSet).withSort(Sort.by(field)));
	}
}