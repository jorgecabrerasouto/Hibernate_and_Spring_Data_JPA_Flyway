package co.com.jorgecabrerasouto.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.jorgecabrerasouto.orderservice.domain.OrderHeader;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {

}
