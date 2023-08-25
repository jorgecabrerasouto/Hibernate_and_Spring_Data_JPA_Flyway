package co.com.jorgecabrerasouto.jdbc.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import co.com.jorgecabrerasouto.jdbc.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	
	Optional<Book> findBookByTitle(String title);
	
	Book readByTitle(String title);
	
	@Nullable
	Book getByTitle(@Nullable String title);
	
}
