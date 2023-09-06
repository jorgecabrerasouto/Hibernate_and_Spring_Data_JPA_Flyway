package co.com.jorgecabrerasouto.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.jorgecabrerasouto.orderservice.domain.OrderApproval;

public interface OrderApprovalRepository extends JpaRepository <OrderApproval, Long>{

}
