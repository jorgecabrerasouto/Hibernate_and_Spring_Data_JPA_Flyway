package co.com.jorgecabrerasouto.jdbc.repositories;


import co.com.jorgecabrerasouto.jdbc.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
	
	Author findAuthorByFirstNameAndLastName(String firstName, String lastName);
	
}
