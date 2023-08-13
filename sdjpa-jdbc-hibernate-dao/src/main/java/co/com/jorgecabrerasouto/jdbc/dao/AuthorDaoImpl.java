package co.com.jorgecabrerasouto.jdbc.dao;

import org.springframework.stereotype.Component;

import co.com.jorgecabrerasouto.jdbc.domain.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

@Component
public class AuthorDaoImpl implements AuthorDao {
   
	private final EntityManagerFactory emf;
	
	public AuthorDaoImpl(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
    public Author getById(Long id) {
        return getEntityManager().find(Author.class, id);
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
    	TypedQuery<Author> query = getEntityManager().createQuery("SELECT a FROM Author a " +
                "WHERE a.firstName = :first_name and a.lastName = :last_name", Author.class);
        query.setParameter("first_name", firstName);
        query.setParameter("last_name", lastName);

        return query.getSingleResult();
    }

    @Override
    public Author saveNewAuthor(Author author) {
    	EntityManager em = getEntityManager();
    	em.getTransaction().begin();
    	em.persist(author);
    	em.flush();
    	em.getTransaction().commit();
        return author;
    }

    @Override
    public Author updateAuthor(Author author) {
        return null;
    }

    @Override
    public void deleteAuthorById(Long id) {

    }
    private EntityManager getEntityManager() {
    	return emf.createEntityManager();
    }
}