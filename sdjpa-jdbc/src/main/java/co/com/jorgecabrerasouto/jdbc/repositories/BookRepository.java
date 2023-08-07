package co.com.jorgecabrerasouto.jdbc.repositories;

import co.com.jorgecabrerasouto.jdbc.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository <Book, Long> {

}
