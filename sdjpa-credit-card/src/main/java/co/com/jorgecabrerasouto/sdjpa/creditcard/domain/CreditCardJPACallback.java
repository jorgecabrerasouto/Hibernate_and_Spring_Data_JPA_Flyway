package co.com.jorgecabrerasouto.sdjpa.creditcard.domain;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class CreditCardJPACallback {

	@PrePersist
	@PreUpdate
	public void beforeInsertOrUpdate(CreditCard creditCard) {
		
		System.out.println("Before update was called...");
		
	}
	
	@PostPersist
	@PostLoad
	@PostUpdate	
	public void postLoad(CreditCard creditCard) {
		
		System.out.println("Post Load was called...");
		
	}
}
