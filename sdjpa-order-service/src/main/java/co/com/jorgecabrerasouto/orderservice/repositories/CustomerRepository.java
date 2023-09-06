package co.com.jorgecabrerasouto.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.jorgecabrerasouto.orderservice.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
