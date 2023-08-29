package co.com.jorgecabrerasouto.jdbc.dao;

import java.util.List;

import co.com.jorgecabrerasouto.jdbc.domain.Book;

public interface BookDao {
	
	List <Book> findAllBooks(int pageSize, int offset);
	
	List <Book> findAllBooks();
    
    Book getById(Long id);

    Book findBookByTitle(String title);

    Book saveNewBook(Book book);

    Book updateBook(Book book);

    void deleteBookById(Long id);

}
