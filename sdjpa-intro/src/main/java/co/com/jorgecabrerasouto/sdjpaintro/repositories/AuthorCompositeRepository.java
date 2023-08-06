package co.com.jorgecabrerasouto.sdjpaintro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.jorgecabrerasouto.sdjpaintro.domain.composite.AuthorComposite;
import co.com.jorgecabrerasouto.sdjpaintro.domain.composite.NameId;

public interface AuthorCompositeRepository extends JpaRepository<AuthorComposite, NameId> {
}

