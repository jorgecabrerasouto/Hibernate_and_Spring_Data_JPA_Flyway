package co.com.jorgecabrerasouto.orderservice.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.jorgecabrerasouto.orderservice.domain.OrderHeader;
import co.com.jorgecabrerasouto.orderservice.repositories.OrderHeaderRepository;

@Service
public class BootstrapOrderService {

	@Autowired
	OrderHeaderRepository orderHeaderRepository;
	
	@Transactional
	public void readOrderData() {
		
		OrderHeader orderHeader = orderHeaderRepository.findById(1L).get();
		
		orderHeader.getOrderLines().forEach(ol -> {
			System.out.println(ol.getProduct().getDescription());
			ol.getProduct().getCategories().forEach(cat -> {
				System.out.println(cat.getDescription());
			});
		});
	}
}
