package co.com.jorgecabrerasouto.java.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import co.com.jorgecabrerasouto.java.domain.Author;

@Component
public class AuthorDaoImpl implements AuthorDao {
    @Override
    public Author getById(Long id) {
        return null;
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        return null;
    }

    @Override
    public Author saveNewAuthor(Author author) {
        return null;
    }

    @Override
    public Author updateAuthor(Author author) {
        return null;
    }

    @Override
    public void deleteAuthorById(Long id) {

    }
    
    private RowMapper<Author> getRowMapper(){
    	return new AuthorMapper();
    }
}