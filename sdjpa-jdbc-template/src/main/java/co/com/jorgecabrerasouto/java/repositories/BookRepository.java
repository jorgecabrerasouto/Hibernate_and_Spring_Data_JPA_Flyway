package co.com.jorgecabrerasouto.java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.jorgecabrerasouto.java.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
