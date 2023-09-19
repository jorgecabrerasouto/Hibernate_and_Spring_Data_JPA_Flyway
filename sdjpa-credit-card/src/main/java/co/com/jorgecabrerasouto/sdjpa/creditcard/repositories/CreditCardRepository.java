package co.com.jorgecabrerasouto.sdjpa.creditcard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.jorgecabrerasouto.sdjpa.creditcard.domain.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long>{

}
