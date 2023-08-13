package co.com.jorgecabrerasouto.jdbc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.jorgecabrerasouto.jdbc.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
