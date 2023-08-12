package co.com.jorgecabrerasouto.java.dao;

import co.com.jorgecabrerasouto.java.domain.Book;

public interface BookDao {
	Book getById(Long id);
	
	Book findBookByTitle(String title);
	
	Book saveNewBook(Book book);
	
	Book updateBook (Book saved);

	void deleteBookById(Long id);
}
