package com.example.backend;

import com.example.backend.models.entity.Cart;
import com.example.backend.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Slf4j
class BackendApplicationTests {

	@Autowired
	private CartRepository cartRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testOptional(){
		Optional<Cart> cart = cartRepository.findCart(4, 1052);
		log.info("log " + cart.isPresent());
	}

}
