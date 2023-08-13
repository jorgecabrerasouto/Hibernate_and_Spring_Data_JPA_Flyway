package co.com.jorgecabrerasouto.jdbc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.jorgecabrerasouto.jdbc.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}