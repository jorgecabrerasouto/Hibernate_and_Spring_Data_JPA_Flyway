package co.com.jorgecabrerasouto.sdjpamultidb.repositories.creditcard;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.jorgecabrerasouto.sdjpamultidb.domain.creditcard.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

}
