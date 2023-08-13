package co.com.jorgecabrerasouto.jdbc.dao;

import co.com.jorgecabrerasouto.jdbc.domain.Book;

public interface BookDao {
    Book getById(Long id);

    Book findBookByTitle(String title);

    Book saveNewBook(Book book);

    Book updateBook(Book book);

    void deleteBookById(Long id);

}
