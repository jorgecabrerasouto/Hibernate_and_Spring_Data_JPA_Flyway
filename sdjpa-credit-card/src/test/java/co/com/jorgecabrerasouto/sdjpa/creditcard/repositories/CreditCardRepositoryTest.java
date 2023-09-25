package co.com.jorgecabrerasouto.sdjpa.creditcard.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import co.com.jorgecabrerasouto.sdjpa.creditcard.domain.CreditCard;
import co.com.jorgecabrerasouto.sdjpa.creditcard.services.EncryptionService;

@SpringBootTest
@ActiveProfiles("local")
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditCardRepositoryTest {
	final String CREDIT_CARD = "12345678900000";
	
	@Autowired
	EncryptionService encryptionService;
	
	@Autowired
	CreditCardRepository creditCardRepository;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Test
	void testSaveAndStoreCreditCard() {
		CreditCard creditCard = new CreditCard();
		creditCard.setCreditCardNumber(CREDIT_CARD);
		creditCard.setCvv("123");
		creditCard.setExpirationDate("12/2028");
		
		CreditCard savedCC = creditCardRepository.saveAndFlush(creditCard);
		
		System.out.println("Getting CC from database: " + creditCard.getCreditCardNumber());
		
		System.out.println("CC at Rest");
		System.out.println("CC Encrypted: " + encryptionService.encrypt(CREDIT_CARD));
		
		Map<String, Object> dbRow = jdbcTemplate.queryForMap("SELECT * FROM credit_card " +
		" WHERE id = " + savedCC.getId());
		
		String dbCardValue = (String) dbRow.get("credit_card_number");
		
        assertThat(savedCC.getCreditCardNumber()).isNotEqualTo(dbCardValue);
        assertThat(dbCardValue).isEqualTo(encryptionService.encrypt(CREDIT_CARD));

        CreditCard fetchedCC = creditCardRepository.findById(savedCC.getId()).get();

        assertThat(savedCC.getCreditCardNumber()).isEqualTo(fetchedCC.getCreditCardNumber());
	}

}
