package co.com.jorgecabrerasouto.sdjpa.creditcard.domain;

import co.com.jorgecabrerasouto.sdjpa.creditcard.config.SpringContextHelper;
import co.com.jorgecabrerasouto.sdjpa.creditcard.services.EncryptionService;
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
		creditCard.setCreditCardNumber(getEncryptionService().encrypt(creditCard.getCreditCardNumber()));
		
	}
	
	@PostPersist
	@PostLoad
	@PostUpdate	
	public void postLoad(CreditCard creditCard) {
		System.out.println("Post Load was called...");
		creditCard.setCreditCardNumber(getEncryptionService().decrypt(creditCard.getCreditCardNumber()));
	}
	
	private EncryptionService getEncryptionService() {
		return SpringContextHelper.getApplicationContext().getBean(EncryptionService.class);
	}
	
}
