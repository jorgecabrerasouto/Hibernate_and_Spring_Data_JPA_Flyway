package co.com.jorgecabrerasouto.sdjpaintro;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import co.com.jorgecabrerasouto.sdjpaintro.domain.AuthorUuid;
import co.com.jorgecabrerasouto.sdjpaintro.domain.BookNatural;
import co.com.jorgecabrerasouto.sdjpaintro.domain.BookUuid;
import co.com.jorgecabrerasouto.sdjpaintro.domain.composite.AuthorComposite;
import co.com.jorgecabrerasouto.sdjpaintro.domain.composite.AuthorEmbedded;
import co.com.jorgecabrerasouto.sdjpaintro.domain.composite.NameId;
import co.com.jorgecabrerasouto.sdjpaintro.repositories.AuthorCompositeRepository;
import co.com.jorgecabrerasouto.sdjpaintro.repositories.AuthorEmbeddedRepository;
import co.com.jorgecabrerasouto.sdjpaintro.repositories.AuthorUuidRepository;
import co.com.jorgecabrerasouto.sdjpaintro.repositories.BookNaturalRepository;
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
    
    @Autowired
    BookNaturalRepository bookNaturalRepository;
    
    @Autowired
    AuthorCompositeRepository authorCompositeRepository;
    
    @Autowired
    AuthorEmbeddedRepository authorEmbeddedRepository;
    
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
    void bookNaturalTest() {
        BookNatural bookNatural = new BookNatural();
        bookNatural.setTitle("My Book");
        BookNatural saved = bookNaturalRepository.save(bookNatural);

        BookNatural fetched = bookNaturalRepository.getById(saved.getTitle());
        assertThat(fetched).isNotNull();
    }
    
    @Test
    void authorCompositeTest() {
        NameId nameId = new NameId("John", "T");
        AuthorComposite authorComposite = new AuthorComposite();
        authorComposite.setFirstName(nameId.getFirstName());
        authorComposite.setLastName(nameId.getLastName());
        authorComposite.setCountry("US");

        AuthorComposite saved = authorCompositeRepository.save(authorComposite);
        assertThat(saved).isNotNull();

        AuthorComposite fetched = authorCompositeRepository.getById(nameId);
        assertThat(fetched).isNotNull();
    }
    
    @Test
    void authorEmbeddedTest() {
        NameId nameId = new NameId("John", "T");
        AuthorEmbedded authorEmbedded = new AuthorEmbedded(nameId);

        AuthorEmbedded saved = authorEmbeddedRepository.save(authorEmbedded);
        assertThat(saved).isNotNull();

        AuthorEmbedded fetched = authorEmbeddedRepository.getById(nameId);
        assertThat(fetched).isNotNull();
    }
    
    @Test
    void testMySQL() {
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(2);

    }
}
