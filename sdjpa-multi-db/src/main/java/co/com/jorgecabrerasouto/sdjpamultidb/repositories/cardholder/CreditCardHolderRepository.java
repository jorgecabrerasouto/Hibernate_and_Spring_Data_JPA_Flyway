package co.com.jorgecabrerasouto.sdjpamultidb.repositories.cardholder;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.jorgecabrerasouto.sdjpamultidb.domain.cardholder.CreditCardHolder;

public interface CreditCardHolderRepository extends JpaRepository<CreditCardHolder, Long> {

}
