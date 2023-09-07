package co.com.jorgecabrerasouto.orderservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class OrderApproval extends BaseEntity {
	
	@OneToOne
	@JoinColumn(name = "order_header_id")
	private OrderHeader orderHeader;
	
	private String ApprovedBy;

	public String getApprovedBy() {
		return ApprovedBy;
	}

	public void setApprovedBy(String approvedBy) {
		ApprovedBy = approvedBy;
	}

	public OrderHeader getOrderHeader() {
		return orderHeader;
	}

	public void setOrderHeader(OrderHeader orderHeader) {
		this.orderHeader = orderHeader;
	}
	

}
