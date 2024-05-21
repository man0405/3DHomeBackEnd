package com.example.backend.services.Impl;

import com.example.backend.dto.FurnitureFilter;
import com.example.backend.dto.FurnitureResponse;
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

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	public Page<FurnitureResponse> searchFurniture(int customerId,String name, String price, String warranty, String material, int offset, int pageSet) {
		System.out.println("Service search " + name);
		int[] price_range = {0,0};
		if (price!= null){
			price_range = Arrays.stream(price.trim().split("-")).mapToInt(Integer::parseInt).toArray();
		}
		return furnitureRepo.searchFurnitureByFilter(customerId,name, price, price_range[0], price_range[1], warranty, material, PageRequest.of(offset - 1, pageSet));
	}

	@Override
	public FurnitureFilter getFilter() {
		List<String> warranty = furnitureRepo.warrantyRange();
		List<String> material = furnitureRepo.materialRange();
		List<String> priceRange = furnitureRepo.priceRange().stream().map(e -> e[0].toString())
				.sorted(Comparator.comparingLong(price -> Long.parseLong(price.split("-")[0])))
						.collect(Collectors.toList());
		return new FurnitureFilter(priceRange, material, warranty);
	}


	public List<Furniture> findFurnituresWithSorting(String field){
		return furnitureRepo.findAll(Sort.by(Sort.Direction.ASC, field));
	}

	public Page<FurnitureResponse> findFurnituresWithPaginationAndSort(int offset, int pageSet, String field, int customerId){
		return furnitureRepo.findFurnitureWithPaginationAndSort(customerId, PageRequest.of(offset, pageSet).withSort(Sort.by(field)));
	}
}