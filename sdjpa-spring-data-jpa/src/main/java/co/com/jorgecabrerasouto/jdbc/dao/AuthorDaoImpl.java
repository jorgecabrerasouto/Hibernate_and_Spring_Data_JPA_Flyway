package co.com.jorgecabrerasouto.jdbc.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import co.com.jorgecabrerasouto.jdbc.domain.Author;
import co.com.jorgecabrerasouto.jdbc.repositories.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Component
public class AuthorDaoImpl implements AuthorDao {
	
	private final AuthorRepository authorRepository;
	
    public AuthorDaoImpl(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

    @Override
    public List<Author> findAllAuthorsByLastName(String lastname, Pageable pageable) {
        return authorRepository.findAuthorByLastName(lastname, pageable).getContent();
    }

	@Override
    public Author getById(Long id) {
        return authorRepository.getReferenceById(id);
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        return authorRepository.findAuthorByFirstNameAndLastName(firstName, lastName)
        		.orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Author saveNewAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Transactional
    @Override
    public Author updateAuthor(Author author) {
    	Author foundAuthor = authorRepository.getReferenceById(author.getId());
    	foundAuthor.setFirstName(author.getFirstName());
    	foundAuthor.setLastName(author.getLastName());
        return authorRepository.save(foundAuthor);
    }

    @Override
    public void deleteAuthorById(Long id) {
    	authorRepository.deleteById(id);
    }
}
