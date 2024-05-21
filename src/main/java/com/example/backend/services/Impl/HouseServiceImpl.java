package com.example.backend.services.Impl;

import com.example.backend.dto.FilterResponse;
import com.example.backend.dto.HouseResponse;
import com.example.backend.repository.HouseRepo;
import com.example.backend.models.entity.House;
import com.example.backend.repository.InformationRepo;
import com.example.backend.services.HouseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HouseServiceImpl implements HouseService {

	private final HouseRepo houseRepo;
	private final InformationRepo informationRepo;

	@Autowired
	public HouseServiceImpl(HouseRepo theHouseRepo, InformationRepo informationRepo) {
		houseRepo = theHouseRepo;
		this.informationRepo = informationRepo;
	}

	@Override
	public List<House> findAll() {
		return houseRepo.findAll();
	}

	@Override
	public House findById(int theId) {
		Optional<House> result = houseRepo.findById(theId);

		House theHouse = null;

		if (result.isPresent()) {
			theHouse = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find owner id - " + theId);
		}
		return theHouse;
	}

	@Override
	@Transactional
	public House save(House theHouse) {
		return houseRepo.save(theHouse);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		houseRepo.deleteById(theId);
	}


	@Override
	public Page<House> findOwnerHouses(int id, int offset, int pageSet, String field) {
		return houseRepo.findHousesByOwner_Id(id, PageRequest.of(offset,pageSet));
	}


	public List<House> findHousesWithSorting(String field){
		return houseRepo.findAll(Sort.by(Sort.Direction.ASC, field));
	}


	public Page<HouseResponse> findHousesWithPaginationAndSort(int offset, int pageSet, String field, Long customerId) {
		return houseRepo.findHousesWithPaginationAndSort(customerId.intValue(), PageRequest.of(offset, pageSet).withSort(Sort.by(field)));
	}
	@Override
	public Page<HouseResponse> searchHouse(Long id, String name, String landSize, int minLandSize, int maxLandSize, int bedRoom, String price, int minPrice, int maxPrice , int offset, int pageSet) {
		return houseRepo.searchHouseByFilter(id,name,landSize,minLandSize,maxLandSize,bedRoom,price,minPrice,maxPrice, PageRequest.of(offset - 1, pageSet));
	}

	@Override
	public FilterResponse getFilter() {
		List<String> rangeSize = houseRepo.landSizeRange().stream()
				.map(obj -> (String) obj[0])
				.sorted(Comparator.comparingLong(filter -> Long.parseLong(filter.split("-")[0]))
				).collect(Collectors.toList());
		List<String> rangePrice =houseRepo.priceRange().stream()
				.map(obj -> (String) obj[0])
				.sorted(Comparator.comparingLong(filter -> Long.parseLong(filter.split("-")[0])))
				.collect(Collectors.toList());
		List<Long> rangeBedroom = houseRepo.bedroomRange();
		return new FilterResponse(rangeSize,rangePrice,rangeBedroom);
	}

	public Page<House> findHousesWithPaginationAndSort(int offset, int pageSet, String field){
		return houseRepo.findAll(PageRequest.of(offset, pageSet).withSort(Sort.by(field)));

	}
}