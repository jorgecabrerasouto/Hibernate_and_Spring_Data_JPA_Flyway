package co.com.jorgecabrerasouto.orderservice.services;

import co.com.jorgecabrerasouto.orderservice.domain.Product;

public interface ProductService {
	
	Product saveProduct (Product product);
	
	Product updateQOH( Long id, Integer quantityOnHand);

}
