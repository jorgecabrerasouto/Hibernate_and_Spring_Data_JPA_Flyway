package co.com.jorgecabrerasouto.jdbc;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import co.com.jorgecabrerasouto.jdbc.repositories.AuthorRepository;
import co.com.jorgecabrerasouto.jdbc.repositories.BookRepository;


@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"co.com.jorgecabrerasouto.sdjpajdbc.bootstrap"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MySQLIntegrationTest {

    @Autowired
    BookRepository bookRepository;
    
    @Autowired
    AuthorRepository authorRepository;

 
    @Test
    void testMySQL() {
        long countBeforeBook = bookRepository.count();
        assertThat(countBeforeBook).isEqualTo(5);
        
        long countBeforeAuthor = authorRepository.count();
        assertThat(countBeforeAuthor).isEqualTo(3);

    }
}