package co.com.jorgecabrerasouto.sdjpaintro.repositories;

import co.com.jorgecabrerasouto.sdjpaintro.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository <Book, Long> {

}
