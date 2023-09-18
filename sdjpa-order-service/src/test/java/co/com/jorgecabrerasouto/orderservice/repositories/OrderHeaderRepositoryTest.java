package co.com.jorgecabrerasouto.orderservice.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import co.com.jorgecabrerasouto.orderservice.domain.Address;
import co.com.jorgecabrerasouto.orderservice.domain.Customer;
import co.com.jorgecabrerasouto.orderservice.domain.OrderApproval;
import co.com.jorgecabrerasouto.orderservice.domain.OrderHeader;
import co.com.jorgecabrerasouto.orderservice.domain.OrderLine;
import co.com.jorgecabrerasouto.orderservice.domain.Product;
import co.com.jorgecabrerasouto.orderservice.domain.ProductStatus;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderHeaderRepositoryTest {

	@Autowired
	OrderHeaderRepository orderHeaderRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	OrderApprovalRepository orderApprovalRepository;

	@Autowired
	ProductRepository productRepository;

	Product product;

	@BeforeEach
	void setUp() {
		Product newProduct = new Product();
		newProduct.setProductStatus(ProductStatus.NEW);
		newProduct.setDescription("test product");
		product = productRepository.saveAndFlush(newProduct);
	}

	@Test
	void testSavedOrderWithLine() {
		OrderHeader orderHeader = new OrderHeader();
		Customer customer = new Customer();
		customer.setCustomerName("New Customer");
		Customer savedCustomer = customerRepository.save(customer);

		orderHeader.setCustomer(savedCustomer);

		OrderLine orderLine = new OrderLine();
		orderLine.setQuantityOrdered(5);
		orderLine.setProduct(product);

		orderHeader.addOrderLine(orderLine);

		OrderApproval approval = new OrderApproval();
		approval.setApprovedBy("JCS");

		orderHeader.setOrderApproval(approval);

		OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);

		orderHeaderRepository.flush();

		assertNotNull(savedOrder);
		assertNotNull(savedOrder.getId());
		assertNotNull(savedOrder.getOrderLines());
		assertEquals(savedOrder.getOrderLines().size(), 1);

		OrderHeader fetchedOrder = orderHeaderRepository.getReferenceById(savedOrder.getId());

		assertNotNull(fetchedOrder);
		assertEquals(fetchedOrder.getOrderLines().size(), 1);

	}

	@Test
	void exceptionTesting() {
		ConstraintViolationException thrown = assertThrows(
				ConstraintViolationException.class, () -> testSaveOrder(),
				"ConstraintViolationException () to throw, but it didn't"	
		);

//		System.out.println("Mensaje: " + thrown.getMessage());
		assertTrue(thrown.getMessage().contains("Validation failed for classes"));
	}

	@Test
	void testSaveOrder() {
		OrderHeader orderHeader = new OrderHeader();
		Customer customer = new Customer();
		customer.setCustomerName("New Customer");
		
		customer.setPhone("01234567890");

		Address address = new Address();
		address.setCity("AAAAAAAAAAAAAAA");
		customer.setAddress(address);

		Customer savedCustomer = customerRepository.save(customer);

		orderHeader.setCustomer(savedCustomer);
		OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);

		assertNotNull(savedOrder);
		assertNotNull(savedOrder.getId());

		OrderHeader fetchedOrder = orderHeaderRepository.getReferenceById(savedOrder.getId());

		assertNotNull(fetchedOrder);
		assertNotNull(fetchedOrder.getId());
		assertNotNull(fetchedOrder.getCreatedDate());
		assertNotNull(fetchedOrder.getLastModifiedDate());
	}

	@Test
	void testDeleteCascade() {

		OrderHeader orderHeader = new OrderHeader();
		Customer customer = new Customer();
		customer.setCustomerName("new Customer");
		orderHeader.setCustomer(customerRepository.save(customer));

		OrderLine orderLine = new OrderLine();
		orderLine.setQuantityOrdered(3);
		orderLine.setProduct(product);

		OrderApproval orderApproval = new OrderApproval();
		orderApproval.setApprovedBy("JCS");
		orderHeader.setOrderApproval(orderApproval);

		orderHeader.addOrderLine(orderLine);
		OrderHeader savedOrder = orderHeaderRepository.saveAndFlush(orderHeader);

		System.out.println("order saved and flushed");

		orderHeaderRepository.deleteById(savedOrder.getId());
		orderHeaderRepository.flush();

		assertThrows(EntityNotFoundException.class, () -> {
			OrderHeader fetchedOrder = orderHeaderRepository.getReferenceById(savedOrder.getId());

			assertNull(fetchedOrder);
		});
	}
}
