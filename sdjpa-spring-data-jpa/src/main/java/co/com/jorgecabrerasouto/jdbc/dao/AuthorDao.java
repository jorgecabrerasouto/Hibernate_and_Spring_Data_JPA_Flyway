package co.com.jorgecabrerasouto.jdbc.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;

import co.com.jorgecabrerasouto.jdbc.domain.Author;

public interface AuthorDao {

    List<Author> findAllAuthorsByLastName(String lastname, Pageable pageable);

    Author getById(Long id);

    Author findAuthorByName(String firstName, String lastName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);
}
