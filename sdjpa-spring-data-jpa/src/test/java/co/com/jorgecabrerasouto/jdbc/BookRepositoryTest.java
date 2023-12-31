package co.com.jorgecabrerasouto.jdbc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import co.com.jorgecabrerasouto.jdbc.domain.Book;
import co.com.jorgecabrerasouto.jdbc.repositories.BookRepository;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"guru.springframework.jdbc.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void testBookJPANamedQuery() {
        Book book = bookRepository.jpaNamed("Clean Code");
        assertThat(book).isNotNull();
    }
    
    @Test
    void testBookQueryNative() {
    	Book book = bookRepository.findBookByTitleNativeQuery("Clean Code");	
    	assertThat(book).isNotNull();
    }
    
    @Test
    void testBookQueryNamed() {
    	Book book = bookRepository.findByTitleWithQueryNamed("Clean Code");
    	assertThat(book).isNotNull();
    }
    
    @Test
    void testBookQuery() {
    	Book book = bookRepository.findBookWithQuery("Clean Code");
    	
    	assertThat(book).isNotNull();
    }
    
    @Test
    void testBookFuture() throws ExecutionException, InterruptedException {
    	Future <Book> bookFuture = bookRepository.queryByTitle("Clean Code");
    	
    	Book book = bookFuture.get();
    	
    	assertNotNull(book);
    	
    }

    @Test
    void testBookStream () {
    	AtomicInteger count = new AtomicInteger();
    	bookRepository.findAllByTitleNotNull().forEach(book -> {
    		count.incrementAndGet();
    	});
    	assertThat(count.get()).isGreaterThan(4);
    }
    @Test
    void testEmptyResultException() {

        assertThrows(EmptyResultDataAccessException.class, () -> {
            Book book = bookRepository.readByTitle("foobar4");
        });
    }

    @Test
    void testNullParam() {
        assertNull(bookRepository.getByTitle(null));
    }

    @Test
    void testNoException() {

        assertNull(bookRepository.getByTitle("foo"));
    }
}