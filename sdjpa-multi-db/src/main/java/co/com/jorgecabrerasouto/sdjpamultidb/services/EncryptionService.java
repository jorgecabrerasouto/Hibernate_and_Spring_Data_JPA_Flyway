package co.com.jorgecabrerasouto.sdjpamultidb.services;

public interface EncryptionService {
	
	 String encrypt(String freeText);

	 String decrypt(String encryptedText);

}
