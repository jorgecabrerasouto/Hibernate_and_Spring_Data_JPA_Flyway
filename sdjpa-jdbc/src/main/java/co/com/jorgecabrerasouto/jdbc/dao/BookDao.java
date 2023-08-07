package co.com.jorgecabrerasouto.jdbc.dao;

import co.com.jorgecabrerasouto.jdbc.domain.Book;

public interface BookDao {
	Book getById(Long id);

}
