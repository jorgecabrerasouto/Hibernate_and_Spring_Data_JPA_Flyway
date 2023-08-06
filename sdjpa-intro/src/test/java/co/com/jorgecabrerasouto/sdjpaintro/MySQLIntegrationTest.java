package co.com.jorgecabrerasouto.sdjpaintro;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import co.com.jorgecabrerasouto.sdjpaintro.domain.AuthorUuid;
import co.com.jorgecabrerasouto.sdjpaintro.domain.BookUuid;
import co.com.jorgecabrerasouto.sdjpaintro.repositories.AuthorUuidRepository;
import co.com.jorgecabrerasouto.sdjpaintro.repositories.BookRepository;
import co.com.jorgecabrerasouto.sdjpaintro.repositories.BookUuidRepository;


@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"co.com.jorgecabrerasouto.sdjpaintro.bootstrap"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MySQLIntegrationTest {

    @Autowired
    BookRepository bookRepository;
    
    @Autowired
    AuthorUuidRepository authorUuidRepository;

    @Autowired
    BookUuidRepository bookUuidRepository;
    
    @Test
    void testBookUuid() {
    	BookUuid bookUuid = bookUuidRepository.save(new BookUuid());
    	assertThat(bookUuid).isNotNull();
    	assertThat(bookUuid.getId()).isNotNull();
    	
    	BookUuid fetched = bookUuidRepository.getById(bookUuid.getId());
    	assertThat(fetched).isNotNull();
    }
    
    @Test
    void testAuthorUuid() {
    	AuthorUuid authorUuid = authorUuidRepository.save(new AuthorUuid());
    	assertThat(authorUuid).isNotNull();
    	assertThat(authorUuid.getId()).isNotNull();
    	
    	AuthorUuid fetched = authorUuidRepository.getById(authorUuid.getId());
    	assertThat(fetched).isNotNull();
    }
    
    @Test
    void testMySQL() {
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(2);

    }
}
