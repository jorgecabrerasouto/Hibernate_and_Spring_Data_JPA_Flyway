package co.com.jorgecabrerasouto.jdbc.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.ActiveProfiles;

import co.com.jorgecabrerasouto.jdbc.domain.Book;

@ActiveProfiles("local")
@DataJpaTest
@Import(BookDaoImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookDaoImplTest {

	@Autowired
	BookDao bookDao;

	@Test
	void findAllBooksPage1_SortByTitle() {
		List<Book> books = bookDao.findAllBooksSortByTitle(PageRequest.of(0, 3, Sort.by(Sort.Order.desc("title"))));

		assertThat(books).isNotNull();
		assertThat(books.size()).isEqualTo(3);

	}

	@Test
	void findAllBooksPage1_pageable() {
		List<Book> books = bookDao.findAllBooks(PageRequest.of(0, 3));

		assertThat(books).isNotNull();
		assertThat(books.size()).isEqualTo(3);

	}

	@Test
	void findAllBooksPage2_pageable() {
		List<Book> books = bookDao.findAllBooks(PageRequest.of(1, 3));

		assertThat(books).isNotNull();
		assertThat(books.size()).isEqualTo(2);

	}

	@Test
	void findAllBooksPage10_pageable() {
		List<Book> books = bookDao.findAllBooks(PageRequest.of(10, 10));

		assertThat(books).isNotNull();
		assertThat(books.size()).isEqualTo(0);

	}

	@Test
	void findAllBooksPage1() {
		List<Book> books = bookDao.findAllBooks(3, 0);

		assertThat(books).isNotNull();
		assertThat(books.size()).isEqualTo(3);

	}

	@Test
	void findAllBooksPage2() {
		List<Book> books = bookDao.findAllBooks(3, 3);

		assertThat(books).isNotNull();
		assertThat(books.size()).isEqualTo(2);

	}

	@Test
	void findAllBooksPage3() {
		List<Book> books = bookDao.findAllBooks(3, 100);

		assertThat(books).isNotNull();
		assertThat(books.size()).isEqualTo(0);

	}

	@Test
	void testFindAllBooks() {
		List<Book> books = bookDao.findAllBooks();

		assertThat(books).isNotNull();
		assertThat(books.size()).isGreaterThan(4);
	}

	@Test
	void getById() {
		Book book = bookDao.getById(3L);

		assertThat(book.getId()).isNotNull();
	}

	@Test
	void findBookByTitle() {
		Book book = bookDao.findBookByTitle("Clean Code");

		assertThat(book).isNotNull();
	}

	@Test
	void saveNewBook() {
		Book book = new Book();
		book.setIsbn("1234");
		book.setPublisher("Self");
		book.setTitle("my book");
		book.setAuthorId(1L);

		Book saved = bookDao.saveNewBook(book);

		assertThat(saved).isNotNull();
	}

	@Test
	void updateBook() {
		Book book = new Book();
		book.setIsbn("1234");
		book.setPublisher("Self");
		book.setTitle("my book");
		book.setAuthorId(1L);
		Book saved = bookDao.saveNewBook(book);

		saved.setTitle("New Book");
		bookDao.updateBook(saved);

		Book fetched = bookDao.getById(saved.getId());

		assertThat(fetched.getTitle()).isEqualTo("New Book");
	}

	@Test
	void deleteBookById() {

		Book book = new Book();
		book.setIsbn("1234");
		book.setPublisher("Self");
		book.setTitle("my book");
		Book saved = bookDao.saveNewBook(book);

		bookDao.deleteBookById(saved.getId());

		assertThrows(JpaObjectRetrievalFailureException.class, () -> {
			bookDao.getById(saved.getId());
		});
	}

}
