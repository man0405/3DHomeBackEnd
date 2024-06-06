package com.example.backend;

import com.example.backend.models.entity.Customer;
import com.example.backend.models.entity.Furniture;
import com.example.backend.models.entity.Item;
import com.example.backend.repository.ItemRepo;
import com.example.backend.services.CustomerService;
import com.example.backend.services.FurnitureService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class BackendApplicationTests {

	@Autowired
	private FurnitureService furnitureService;
	@Autowired
	private CustomerService customerService;
    @Autowired
    private ItemRepo itemRepo;

	@Test
	void contextLoads() {
	}

	@Test
	void addItem(){
		Customer customer = customerService.findById(Long.valueOf(2));
		Furniture furniture = furnitureService.findById(Long.valueOf(1));
		Item item = Item.builder().customerId(customer).localDate(LocalDate.now()).furnitureId(furniture).quantity(3).build();
		itemRepo.save(item);
	}

}
