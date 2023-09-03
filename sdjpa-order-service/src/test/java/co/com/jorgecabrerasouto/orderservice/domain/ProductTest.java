package co.com.jorgecabrerasouto.orderservice.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProductTest {

	@Test
	void testEquals() {
		Product p1 = new Product();
		p1.setId(1L);
		
		Product p2 = new Product();
		p2.setId(1L);
		
		assert p1.equals(p2);
	}

	@Test
	void testNotEquals() {
		Product p1 = new Product();
		p1.setId(1L);
		
		Product p2 = new Product();
		p2.setId(3L);
		
		assertFalse(p1.equals(p2));
	}
}
