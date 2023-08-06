package co.com.jorgecabrerasouto.sdjpaintro.repositories;


import java.util.UUID;

import co.com.jorgecabrerasouto.sdjpaintro.domain.AuthorUuid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorUuidRepository extends JpaRepository<AuthorUuid, UUID> {
}