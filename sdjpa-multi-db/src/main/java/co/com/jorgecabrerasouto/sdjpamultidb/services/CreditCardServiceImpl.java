package co.com.jorgecabrerasouto.sdjpamultidb.services;

import org.springframework.stereotype.Service;

import co.com.jorgecabrerasouto.sdjpamultidb.domain.cardholder.CreditCardHolder;
import co.com.jorgecabrerasouto.sdjpamultidb.domain.creditcard.CreditCard;
import co.com.jorgecabrerasouto.sdjpamultidb.domain.pan.CreditCardPAN;
import co.com.jorgecabrerasouto.sdjpamultidb.repositories.cardholder.CreditCardHolderRepository;
import co.com.jorgecabrerasouto.sdjpamultidb.repositories.creditcard.CreditCardRepository;
import co.com.jorgecabrerasouto.sdjpamultidb.repositories.pan.CreditCardPANRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {
	
	private final CreditCardHolderRepository creditCardHolderRepository;
	private final CreditCardRepository creditCardRepository;
	private final CreditCardPANRepository creditCardPANRepository;
	
	@Override
    public CreditCard getCreditCardById(Long id) {
        //todo impl

        return null;
    }
	
	@Transactional
    @Override
	public CreditCard saveCreditCard(CreditCard cc) {
		CreditCard savedCC = creditCardRepository.save(cc);
		savedCC.setFirstName(cc.getFirstName());
		savedCC.setLastName(cc.getLastName());
		savedCC.setZipCode(cc.getZipCode());
		savedCC.setCreditCardNumber(cc.getCreditCardNumber());

		creditCardHolderRepository.save(CreditCardHolder.builder()
				.creditCardId(savedCC.getId())
				.firstName(savedCC.getFirstName())
				.lastName(savedCC.getLastName())
				.zipCode(savedCC.getZipCode())
				.build());
		
		creditCardPANRepository.save(CreditCardPAN.builder()
				.creditCardNumber(savedCC.getCreditCardNumber())
				.creditCardId(savedCC.getId())
				.build());
		
		return savedCC;
	}


}
