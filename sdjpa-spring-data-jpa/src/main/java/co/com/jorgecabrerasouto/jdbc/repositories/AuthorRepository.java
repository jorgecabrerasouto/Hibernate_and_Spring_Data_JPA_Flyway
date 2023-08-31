package co.com.jorgecabrerasouto.jdbc.repositories;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import co.com.jorgecabrerasouto.jdbc.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
	
	Optional<Author> findAuthorByFirstNameAndLastName(String firstName, String lastName);
	
	Page<Author> findAuthorByLastName(String lastName, Pageable pageable);
	
}
