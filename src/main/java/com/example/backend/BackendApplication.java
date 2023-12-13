package com.example.backend;

import com.example.backend.models.entity.FacingDirection;
import com.example.backend.repository.HouseRepo;
import com.example.backend.repository.OwnerRepo;
import com.example.backend.models.entity.House;
import com.example.backend.models.entity.Owner;
import com.example.backend.services.OwnerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(OwnerService theOwnerService, HouseRepo theHouseRepo){
		return runner ->{
//			addOwner(theOwnerService);
//			addHouse(theOwnerService, theHouseRepo);
//			findOwnerById(theOwnerService);
//			findOwnerAndHouses(theOwnerService);

		};
	}

	private void findOwnerAndHouses(OwnerService theOwnerService) {
		int theId = 1;
		Owner theOwner = theOwnerService.getOwnerAlongWithHouses(theId);
		System.out.println(theOwner);
		System.out.println(theOwner.getHouses());
	}

	private void findOwnerById(OwnerService theOwnerService) {
		int theId = 1;
		Owner theOwner = theOwnerService.findById(theId);
		System.out.println(theOwner);
		System.out.println(theOwner.getHouses());
	}

	private void addHouse(OwnerService theOwnerService, HouseRepo theHouseRepo) {
		List<House> houses = new ArrayList<>();
		houses.add(new House(1000000, 1, "Main Street", "New York", "NY", "USA", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1939213, 2, "Oak Avenue", "London", "", "United Kingdom", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1003002, 3, "Maple Lane", "Paris", "", "France", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1949800, 4, "Pine Street", "Sydney", "NSW", "Australia", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1002909, 5, "Cedar Road", "Tokyo", "", "Japan", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1399390, 6, "Elm Boulevard", "Berlin", "", "Germany", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1903090, 7, "Birch Street", "Beijing", "", "China", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1993890, 8, "Willow Avenue", "Rio de Janeiro", "", "Brazil", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1020830, 9, "Redwood Lane", "Moscow", "", "Russia", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1882890, 10, "Olive Road", "Cape Town", "", "South Africa", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1000000, 11, "Juniper Avenue", "Toronto", "ON", "Canada", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1939213, 12, "Walnut Road", "Rome", "", "Italy", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1003002, 13, "Sycamore Street", "Delhi", "", "India", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1949800, 14, "Cedar Lane", "Seoul", "", "South Korea", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1002909, 15, "Walnut Road", "Mexico City", "CDMX", "Mexico", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1399390, 16, "Pine Street", "Amsterdam", "", "Netherlands", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1903090, 17, "Olive Road", "Dubai", "", "UAE", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1993890, 18, "Mahogany Drive", "Buenos Aires", "", "Argentina", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1020830, 19, "Birch Avenue", "Singapore", "", "Singapore", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1882890, 20, "Willow Lane", "Stockholm", "", "Sweden", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1000000, 21, "Elm Avenue", "Istanbul", "", "Turkey", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1939213, 22, "Redwood Drive", "Cairo", "", "Egypt", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1003002, 23, "Juniper Street", "Sydney", "NSW", "Australia", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1949800, 24, "Birch Boulevard", "Athens", "", "Greece", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1002909, 25, "Cedar Lane", "Nairobi", "", "Kenya", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1399390, 26, "Olive Road", "Bangkok", "", "Thailand", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1903090, 27, "Mahogany Avenue", "Lima", "", "Peru", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1993890, 28, "Birch Lane", "Helsinki", "", "Finland", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1020830, 29, "Sycamore Drive", "Jakarta", "", "Indonesia", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1882890, 30, "Pine Road", "Bogota", "", "Colombia", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1000000, 31, "Willow Lane", "Dublin", "", "Ireland", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1939213, 32, "Redwood Avenue", "Tel Aviv", "", "Israel", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1003002, 33, "Cedar Road", "Warsaw", "", "Poland", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1949800, 34, "Elm Street", "Manila", "", "Philippines", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1002909, 35, "Olive Avenue", "Oslo", "", "Norway", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1399390, 36, "Mahogany Road", "Riyadh", "", "Saudi Arabia", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1903090, 37, "Birch Lane", "Kuala Lumpur", "", "Malaysia", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1993890, 38, "Pine Boulevard", "Budapest", "", "Hungary", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1020830, 39, "Willow Avenue", "Casablanca", "", "Morocco", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1882890, 40, "Juniper Street", "Santiago", "", "Chile", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1000000, 41, "Sycamore Drive", "Prague", "", "Czech Republic", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1939213, 42, "Cedar Road", "Johannesburg", "", "South Africa", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1003002, 43, "Redwood Boulevard", "Bucharest", "", "Romania", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1949800, 44, "Elm Avenue", "Auckland", "", "New Zealand", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1002909, 45, "Mahogany Lane", "Vienna", "", "Austria", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1399390, 46, "Olive Lane", "Brussels", "", "Belgium", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1903090, 47, "Willow Street", "Amsterdam", "", "Netherlands", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1993890, 48, "Pine Road", "Kuala Lumpur", "", "Malaysia", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1020830, 49, "Sycamore Drive", "Mexico City", "CDMX", "Mexico", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		houses.add(new House(1882890, 50, "Cedar Avenue", "Moscow", "", "Russia", 28890.58, 2, FacingDirection.NORTH, 5, 5));
		for (int i = 1; i <= 50; i++){
			Owner theOwner = theOwnerService.findById(i);
			House theHouse = houses.get(i - 1);
			theHouse.setOwner(theOwner);
			theOwner.addHouse(theHouse);
			try{
				theOwnerService.update(theOwner);
			}catch (Exception e){
				e.printStackTrace();
			}
		}


	}

	private void addOwner(OwnerService theOwnerService) {
		Owner theOwner = new Owner("Gia", "Thinh", "giathinh824@gmail.com", "0774562721");
		theOwner.addHouse(new House(1123000, 32, "Baker Street", "Southern Province", "Helsinki", "Finland", 28890.58, 2, FacingDirection.EAST, 5, 3));
		theOwner.addHouse(new House(1456000, 91,"Main Street", "Southern Province", "Helsinki", "Finland", 28890.58, 2, FacingDirection.NORTHEST, 3,2));
		theOwnerService.save(theOwner);
	}

}

