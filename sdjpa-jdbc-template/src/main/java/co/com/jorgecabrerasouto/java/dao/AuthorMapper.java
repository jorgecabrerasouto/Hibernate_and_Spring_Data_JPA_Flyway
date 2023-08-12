package co.com.jorgecabrerasouto.java.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import co.com.jorgecabrerasouto.java.domain.Author;
import co.com.jorgecabrerasouto.java.domain.Book;

public class AuthorMapper implements RowMapper<Author> {
	@Override
	public Author mapRow(ResultSet rs, int rowNum) throws SQLException {

		Author author = new Author();
		author.setId(rs.getLong("id"));
		author.setFirstName(rs.getString("first_name"));
		author.setLastName(rs.getString("last_name"));
		
		try {
			if (rs.getString("isbn") != null) {
				author.setBooks(new ArrayList<>());
				author.getBooks().add(mapBook(rs));

				while (rs.next()) {
					author.getBooks().add(mapBook(rs));
				}
			}
		} catch (SQLException e) {
			// do nothing
		}
		return author;
	}

	private Book mapBook(ResultSet rs) throws SQLException {
		Book book = new Book();
		book.setId(rs.getLong(4));
		book.setIsbn(rs.getString(5));
		book.setPublisher(rs.getString(6));
		book.setTitle(rs.getString(7));
		book.setAuthorId(rs.getLong(1));
		return book;

	}

}
