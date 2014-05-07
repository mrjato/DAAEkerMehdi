package es.uvigo.esei.daa.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import es.uvigo.esei.daa.entities.Book;

public class BooksDAO extends DAO {
	public Book get(int id)
	throws DAOException, IllegalArgumentException {
		// Get a connection with the database.
		try (final Connection conn = this.getConnection()) {
			final String query = "SELECT * FROM books WHERE id=?";
			
			// Statement allows you to execute a query.
			try (PreparedStatement statement = conn.prepareStatement(query)) {
				// First question mark is replaced with the value of id.
				statement.setInt(1, id);
				
				// Query (statement) is executed and we get the result in ResultSet
				try (ResultSet result = statement.executeQuery()) {
					// We can iterate through the rows using .next()
					if (result.next()) {
						/*return new Book(
							result.getInt("id"), // We get the value of the "id" column as an integer
							result.getString("title"), // We get the value of the "name" column as a string
							result.getString("surname")*/
						return new Book(result.getInt("id"),
								result.getString("Title"),
								result.getString("Author"),
								result.getString("ISBN"),
								result.getString("Kind"),
								result.getString("EditionLanguage"),
								result.getDate("ReleaseDate"),
								result.getString("Description")
						);
					} else {
						throw new IllegalArgumentException("Invalid id");
					}
				}
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
	public List<Book> list() throws DAOException {
		try (final Connection conn = this.getConnection()) {
			try (Statement statement = conn.createStatement()) {
				try (ResultSet result = statement.executeQuery("SELECT * FROM books")) {
					final List<Book> books = new LinkedList<>();
					
					while (result.next()) {
						books.add(new Book(result.getInt("id"),
								result.getString("Title"),
								result.getString("Author"),
								result.getString("ISBN"),
								result.getString("Kind"),
								result.getString("EditionLanguage"),
								result.getDate("ReleaseDate"),
								result.getString("Description")
						));
					}
					
					return books;
				}
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
	public void delete(int id)
	throws DAOException, IllegalArgumentException {
		try (final Connection conn = this.getConnection()) {
			final String query = "DELETE FROM books WHERE id=?";
			
			try (PreparedStatement statement = conn.prepareStatement(query)) {
				statement.setInt(1, id);
				
				if (statement.executeUpdate() != 1) {
					throw new IllegalArgumentException("Invalid id");
				}
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
	public Book modify(int id,String title, String author, String isbn, String kind, String editionLanguage, Date releaseDate, String description)
	throws DAOException, IllegalArgumentException {
		if (title == null || author == null ||isbn == null || kind == null ||editionLanguage == null || releaseDate == null) {
			throw new IllegalArgumentException("All the fields should be filled except description.");
		}
		
		try (final Connection conn = this.getConnection()) {
			final String query = "UPDATE books SET Title=?, Author=?, ISBN=?,Kind=?,EditionLanguage=?,ReleaseDate=?,Descriptiom=?  WHERE id=?";
			
			try (PreparedStatement statement = conn.prepareStatement(query)) {
				statement.setString(1, title);
				statement.setString(2, author);
				statement.setString(3, isbn);
				statement.setString(4, kind);
				statement.setString(5, editionLanguage);
				statement.setDate(6, releaseDate);
				statement.setString(7, description);
				statement.setInt(8, id);
				
				if (statement.executeUpdate() == 1) {
					return new Book(id, title,author,isbn,kind,editionLanguage,releaseDate,description); 
				} else {
					throw new IllegalArgumentException("All the fields should be filled except description.");
				}
			}
		} catch (SQLException e) {
			throw new DAOException();
		}
	}
	
	public Book add(String title, String author, String isbn, String kind, String editionLanguage, Date releaseDate, String description)
	throws DAOException, IllegalArgumentException {
		if (title == null || author == null ||isbn == null || kind == null ||editionLanguage == null || releaseDate == null) {
			throw new IllegalArgumentException("All the fields should be filled except description.");
		}
		
		try (final Connection conn = this.getConnection()) {
			final String query = "INSERT INTO books VALUES(null, ?, ?, ?, ?, ?, ?, ?)";
			
			try (PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, title);
				statement.setString(2, author);
				statement.setString(3, isbn);
				statement.setString(4, kind);
				statement.setString(5, editionLanguage);
				statement.setDate(6, releaseDate);
				statement.setString(7, description);
				
				if (statement.executeUpdate() == 1) {
					try (ResultSet resultKeys = statement.getGeneratedKeys()) {
						if (resultKeys.next()) {
							return new Book(resultKeys.getInt(1), title,author,isbn,kind,editionLanguage,releaseDate,description);
						} else {
							throw new SQLException("Error retrieving inserted id");
						}
					}
				} else {
					throw new SQLException("Error inserting value");
				}
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
}
