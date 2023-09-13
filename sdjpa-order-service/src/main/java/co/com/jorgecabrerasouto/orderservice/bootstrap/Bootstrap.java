package co.com.jorgecabrerasouto.orderservice.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import co.com.jorgecabrerasouto.orderservice.domain.Customer;
import co.com.jorgecabrerasouto.orderservice.repositories.CustomerRepository;
import co.com.jorgecabrerasouto.orderservice.repositories.OrderHeaderRepository;

@Component
public class Bootstrap implements CommandLineRunner {
	
	@Autowired
	OrderHeaderRepository orderHeaderRepository;
	
	@Autowired
	BootstrapOrderService bootstrapOrderService;
	
	@Autowired
	CustomerRepository customerRepository;
//	
//	@Transactional
//	public void readOrderData() {
//		
//		OrderHeader orderHeader = orderHeaderRepository.findById(1L).get();
//		
//		orderHeader.getOrderLines().forEach(ol -> {
//			System.out.println(ol.getProduct().getDescription());
//			ol.getProduct().getCategories().forEach(cat -> {
//				System.out.println(cat.getDescription());
//			});
//		});
//	}

	@Override
	public void run (String... args) throws Exception {
		bootstrapOrderService.readOrderData();
		
		Customer customer = new Customer();
		customer.setCustomerName("Testing Version");
		Customer savedCustomer = customerRepository.save(customer);
		
		System.out.println("version is: " + savedCustomer.getVersion());
		
		customerRepository.deleteById(savedCustomer.getId());
	}
}
