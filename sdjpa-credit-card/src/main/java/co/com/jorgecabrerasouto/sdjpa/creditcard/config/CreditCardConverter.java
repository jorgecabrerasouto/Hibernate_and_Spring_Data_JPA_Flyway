package co.com.jorgecabrerasouto.sdjpa.creditcard.config;

import co.com.jorgecabrerasouto.sdjpa.creditcard.services.EncryptionService;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CreditCardConverter implements AttributeConverter<String, String> {

	@Override
	public String convertToDatabaseColumn(String attribute) {
		// TODO Auto-generated method stub
		return getEncryptionService().encrypt(attribute);
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		
		return getEncryptionService().decrypt(dbData);
	}
	
	private EncryptionService getEncryptionService() {
		return SpringContextHelper.getApplicationContext().getBean(EncryptionService.class);
	}

}
