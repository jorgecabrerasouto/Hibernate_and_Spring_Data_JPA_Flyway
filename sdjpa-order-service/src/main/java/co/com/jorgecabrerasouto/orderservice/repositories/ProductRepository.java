package co.com.jorgecabrerasouto.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.jorgecabrerasouto.orderservice.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}