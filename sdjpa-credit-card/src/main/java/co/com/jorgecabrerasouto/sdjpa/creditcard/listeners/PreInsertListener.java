package co.com.jorgecabrerasouto.sdjpa.creditcard.listeners;

import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.springframework.stereotype.Component;

import co.com.jorgecabrerasouto.sdjpa.creditcard.services.EncryptionService;

@Component
public class PreInsertListener extends AbstractEncryptionListener implements PreInsertEventListener {

	public PreInsertListener(EncryptionService encryptionService) {
		super(encryptionService);
	}

	@Override
	public boolean onPreInsert(PreInsertEvent event) {
		System.out.println("In Pre Insert");
		
		this.encrypt(event.getState(), event.getPersister().getPropertyNames(), event.getEntity());
		
		return false;
	}

}
