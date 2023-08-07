package co.com.jorgecabrerasouto.jdbc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import co.com.jorgecabrerasouto.jdbc.domain.Author;

public class AuthorDaoImpl implements AuthorDao {

	private final DataSource source;

	public AuthorDaoImpl(DataSource source) {

		this.source = source;
	}

	@Override
	public Author getById(Long id) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = source.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM author WHERE id = " + id);
			if (resultSet.next()) {
				Author author = new Author();
				author.setId(id);
				author.setFirstName(resultSet.getString("first_name"));
				author.setLastName(resultSet.getString("last_name"));

				return author;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
