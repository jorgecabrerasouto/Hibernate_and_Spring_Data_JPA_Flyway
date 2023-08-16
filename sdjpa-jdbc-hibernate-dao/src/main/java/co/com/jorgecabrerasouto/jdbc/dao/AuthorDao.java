package co.com.jorgecabrerasouto.jdbc.dao;

import java.util.List;

import co.com.jorgecabrerasouto.jdbc.domain.Author;

public interface AuthorDao {	
	List<Author> findAll();
	
	List<Author> listAuthorByLastNameLike(String lastName);
	
	Author getById(Long id);

	Author findAuthorByName(String firstName, String lastName);

	Author saveNewAuthor(Author author);

	Author updateAuthor(Author author);

	void deleteAuthorById(Long id);
	
	Author findAuthorByNameCriteria(String firstName, String lastName);
	
	Author findAuthorByNameNative(String firstName, String lastName);
	
}