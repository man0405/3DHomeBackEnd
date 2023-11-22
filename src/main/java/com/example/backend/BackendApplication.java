package com.example.backend;

import com.example.backend.repository.OwnerRepo;
import com.example.backend.models.entity.House;
import com.example.backend.models.entity.Owner;
import com.example.backend.services.OwnerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(OwnerService theOwnerService, OwnerRepo theOwnerRepository){
		return runner ->{
//			addOwner(theOwnerService);
//			addHouse(theOwnerService, theOwnerRepository);
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

	private void addHouse(OwnerService theOwnerService, OwnerRepo theownerRepo) {
		int theId = 2;
		Owner theOwner = theOwnerService.findById(theId);
		theOwner.addHouse(new House("34 Nguyen ","1000m2" ));
		theOwner.addHouse(new House("56 Tran","800m2" ));
		try{
			theOwnerService.update(theOwner);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	private void addOwner(OwnerService theOwnerService) {
		Owner theOwner = new Owner("Gia", "Thinh", "giathinh824@gmail.com", "0774562721");
		theOwner.addHouse(new House("34 Pham","50m2" ));
		theOwner.addHouse(new House("36 Pham","10m2"));
//		theOwner.addHouse(new House("Nguyen Thanh Han", "100m2"));
		theOwnerService.save(theOwner);
	}

}

