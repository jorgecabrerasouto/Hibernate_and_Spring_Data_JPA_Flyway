package co.com.jorgecabrerasouto.java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.jorgecabrerasouto.java.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}