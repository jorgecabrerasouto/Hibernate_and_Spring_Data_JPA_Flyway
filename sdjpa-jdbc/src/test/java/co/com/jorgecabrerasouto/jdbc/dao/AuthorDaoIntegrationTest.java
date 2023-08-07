package co.com.jorgecabrerasouto.jdbc.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import co.com.jorgecabrerasouto.jdbc.domain.Author;

@ActiveProfiles("local")
@DataJpaTest
//@ComponentScan(basePackages= {"co.com.jorgecabrerasouto.jdbc.dao"})
@Import(AuthorDaoImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class AuthorDaoIntegrationTest {

	@Autowired
	AuthorDao authorDao;
	
	@Test
	void testGetAuthor() {
		
		Author author = authorDao.getById(1L);
		assertThat(author).isNotNull();

	}
}
