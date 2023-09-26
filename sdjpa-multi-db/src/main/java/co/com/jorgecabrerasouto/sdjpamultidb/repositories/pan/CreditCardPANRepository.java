package co.com.jorgecabrerasouto.sdjpamultidb.repositories.pan;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.jorgecabrerasouto.sdjpamultidb.domain.pan.CreditCardPAN;

public interface CreditCardPANRepository extends JpaRepository<CreditCardPAN, Long> {
}
