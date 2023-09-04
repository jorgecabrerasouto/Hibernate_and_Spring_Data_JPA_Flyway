package co.com.jorgecabrerasouto.orderservice.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import co.com.jorgecabrerasouto.orderservice.domain.OrderHeader;
import co.com.jorgecabrerasouto.orderservice.domain.OrderLine;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderHeaderRepositoryTest {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;
    
    @Test
    
    void testSavedOrderWithLine() {
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer("New Customer");
        OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);

        OrderLine orderLine = new OrderLine();
        orderLine.setQuantityOrdered(5);
        
        orderHeader.setOrderLines(Set.of(orderLine));
        orderLine.setOrderHeader(orderHeader);        
        
        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());
        assertNotNull(savedOrder.getOrderLines());
        assertEquals(savedOrder.getOrderLines().size(), 1);
        
    }
    
    @Test
    void testSaveOrder() {
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer("New Customer");
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
