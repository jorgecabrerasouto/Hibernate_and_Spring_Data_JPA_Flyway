package co.com.jorgecabrerasouto.orderservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.jorgecabrerasouto.orderservice.domain.Customer;
import co.com.jorgecabrerasouto.orderservice.domain.OrderHeader;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {
	List<OrderHeader> findAllByCustomer(Customer customer);
}
