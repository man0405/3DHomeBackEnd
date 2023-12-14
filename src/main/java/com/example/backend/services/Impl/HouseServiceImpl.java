package com.example.backend.services.Impl;

import com.example.backend.dto.FileDataResponse;
import com.example.backend.dto.HouseResponse;
import com.example.backend.models.entity.FileData;
import com.example.backend.models.entity.Information;
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

import java.io.File;
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
		} else {
			// we didn't find the employee
			throw new RuntimeException("Did not find owner id - " + theId);
		}

		return theHouse;
	}


	@Override
	@Transactional
	public House save(House theHouse) {
		theHouse.addImage(new FileData());
		return houseRepo.save(theHouse);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		houseRepo.deleteById(theId);
	}

	public List<House> findHousesWithSorting(String field){
		return houseRepo.findAll(Sort.by(Sort.Direction.ASC, field));
	}

	public Page<HouseResponse> findHousesWithPaginationAndSort(int offset, int pageSet, String field){
		return houseRepo.findAll(PageRequest.of(offset, pageSet).withSort(Sort.by(field))).map(
				house -> new HouseResponse(
						house.getId(),
						house.getName(),
						house.getDescription(),
						house.getImages().get(0)
				)
		);
	}
}






