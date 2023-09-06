package co.com.jorgecabrerasouto.orderservice.domain;

import jakarta.persistence.Entity;

@Entity
public class OrderApproval extends BaseEntity {
	
	private String ApprovedBy;

	public String getApprovedBy() {
		return ApprovedBy;
	}

	public void setApprovedBy(String approvedBy) {
		ApprovedBy = approvedBy;
	}
	

}
