package co.com.jorgecabrerasouto.orderservice.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import co.com.jorgecabrerasouto.orderservice.domain.Customer;
import co.com.jorgecabrerasouto.orderservice.domain.Product;
import co.com.jorgecabrerasouto.orderservice.domain.ProductStatus;
import co.com.jorgecabrerasouto.orderservice.repositories.CustomerRepository;
import co.com.jorgecabrerasouto.orderservice.repositories.OrderHeaderRepository;
import co.com.jorgecabrerasouto.orderservice.services.ProductService;

@Component
public class Bootstrap implements CommandLineRunner {
	
	@Autowired
	OrderHeaderRepository orderHeaderRepository;
	
	@Autowired
	BootstrapOrderService bootstrapOrderService;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	ProductService productService;

	public void updateProduct() {
        Product product = new Product();
        product.setDescription("My Product");
        product.setProductStatus(ProductStatus.NEW);

        Product savedProduct = productService.saveProduct(product);

        Product savedProduct2 = productService.updateQOH(savedProduct.getId(), 25);

        System.out.println("Updatd Qty: " + savedProduct2.getQuantityOnHand());
	}

	@Override
	public void run (String... args) throws Exception {
		
		updateProduct();
		bootstrapOrderService.readOrderData();
		
		Customer customer = new Customer();
		customer.setCustomerName("Testing Version");
		Customer savedCustomer = customerRepository.save(customer);
		System.out.println("version is: " + savedCustomer.getVersion());
		
		savedCustomer.setCustomerName("Testing version 2");
		Customer savedCustomer2 = customerRepository.save(savedCustomer);
		System.out.println("version is: " + savedCustomer2.getVersion());
		
		savedCustomer2.setCustomerName("Testing version 3");
		Customer savedCustomer3 = customerRepository.save(savedCustomer2);
		System.out.println("version is: " + savedCustomer3.getVersion());
		
		customerRepository.deleteById(savedCustomer.getId());
	}
}
