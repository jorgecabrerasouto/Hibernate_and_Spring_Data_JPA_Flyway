package co.com.jorgecabrerasouto.orderservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import co.com.jorgecabrerasouto.orderservice.domain.Product;
import jakarta.persistence.LockModeType;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Optional<Product> findByDescription(String description); 
	
	@Override
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<Product> findById(Long aLong);
}