package co.com.jorgecabrerasouto.jdbc.repositories;

import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;

import co.com.jorgecabrerasouto.jdbc.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	
    Book jpaNamed(@Param("title") String title);
	
	@Query(value = "SELECT * FROM book WHERE title = :title", nativeQuery = true)
	Book findBookByTitleNativeQuery(@Param("title") String title);
	
	@Query("SELECT b FROM Book b WHERE b.title = :title")
	Book findByTitleWithQueryNamed(@Param("title") String title);
	
	@Query("SELECT b FROM Book b WHERE b.title = ?1")
	Book findBookWithQuery(String string);
	
	Optional<Book> findBookByTitle(String title);
	
	Book readByTitle(String title);
	
	@Nullable
	Book getByTitle(@Nullable String title);
	
	Stream<Book> findAllByTitleNotNull();
	
	@Async
	Future<Book> queryByTitle(String title);
}
