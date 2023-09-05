package co.com.jorgecabrerasouto.orderservice.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import co.com.jorgecabrerasouto.orderservice.domain.Customer;
import co.com.jorgecabrerasouto.orderservice.domain.OrderHeader;
import co.com.jorgecabrerasouto.orderservice.domain.OrderLine;
import co.com.jorgecabrerasouto.orderservice.domain.Product;
import co.com.jorgecabrerasouto.orderservice.domain.ProductStatus;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderHeaderRepositoryTest {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;
    
    @Autowired
    CustomerRepository customerRepository;
    
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
    void testSaveOrder() {
        OrderHeader orderHeader = new OrderHeader();
        Customer customer = new Customer();       
        customer.setCustomerName("New Customer");
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
}
