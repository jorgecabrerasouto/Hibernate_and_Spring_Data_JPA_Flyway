package co.com.jorgecabrerasouto.sdjpaintro.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.jorgecabrerasouto.sdjpaintro.domain.BookUuid;

public interface BookUuidRepository extends JpaRepository<BookUuid, UUID> {
}
