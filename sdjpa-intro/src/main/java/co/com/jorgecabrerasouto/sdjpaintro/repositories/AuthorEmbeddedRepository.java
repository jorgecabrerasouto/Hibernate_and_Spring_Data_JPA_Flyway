package co.com.jorgecabrerasouto.sdjpaintro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.jorgecabrerasouto.sdjpaintro.domain.composite.AuthorEmbedded;
import co.com.jorgecabrerasouto.sdjpaintro.domain.composite.NameId;

public interface AuthorEmbeddedRepository extends JpaRepository<AuthorEmbedded, NameId>{	
}
