package co.com.jorgecabrerasouto.sdjpamultidb.repositories.pan;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.jorgecabrerasouto.sdjpamultidb.domain.cardholder.CreditCardHolder;
import co.com.jorgecabrerasouto.sdjpamultidb.domain.pan.CreditCardPAN;

public interface CreditCardPANRepository extends JpaRepository<CreditCardPAN, Long> {
	Optional<CreditCardPAN> findByCreditCardId(Long id);
}
