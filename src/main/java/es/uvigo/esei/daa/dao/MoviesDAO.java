package es.uvigo.esei.daa.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import es.uvigo.esei.daa.entities.Movie;

public class MoviesDAO extends DAO {
	public Movie get(int id)
	throws DAOException, IllegalArgumentException {
		// Get a connection with the database.
		try (final Connection conn = this.getConnection()) {
			final String query = "SELECT * FROM movies WHERE id=?";
			
			// Statement allows you to execute a query.
			try (PreparedStatement statement = conn.prepareStatement(query)) {
				// First question mark is replaced with the value of id.
				statement.setInt(1, id);
				
				// Query (statement) is executed and we get the result in ResultSet
				try (ResultSet result = statement.executeQuery()) {
					// We can iterate through the rows using .next()
					if (result.next()) {
						return new Movie(
							result.getInt("id"), // We get the value of the "id" column as an integer
							result.getString("Title"), // We get the value of the "name" column as a string
							result.getString("Director"),
							result.getString("Writer"),
							result.getString("Genres"),
							result.getString("Country"),
							result.getString("Language"),
							result.getDate("ReleaseDate"),
							result.getString("Cast"),
							result.getString("RunTime"),
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
	
	public List<Movie> list() throws DAOException {
		try (final Connection conn = this.getConnection()) {
			try (Statement statement = conn.createStatement()) {
				try (ResultSet result = statement.executeQuery("SELECT * FROM movies")) {
					final List<Movie> people = new LinkedList<>();
					
					while (result.next()) {
						people.add(new Movie(
								result.getInt("id"),
								result.getString("Title"),
								result.getString("Director"),
								result.getString("Writer"),
								result.getString("Genres"),
								result.getString("Country"),
								result.getString("Language"),
								result.getDate("ReleaseDate"),
								result.getString("Cast"),
								result.getString("RunTime"),
								result.getString("Description")
						));
					}
					
					return people;
				}
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
	public void delete(int id)
	throws DAOException, IllegalArgumentException {
		try (final Connection conn = this.getConnection()) {
			final String query = "DELETE FROM movies WHERE id=?";
			
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
	
	public Movie modify(int id, String title, String director, String writer, String genres, String country, String language,
			Date releaseDate, String cast, String runTime, String description )
	
	throws DAOException, IllegalArgumentException {
		if (title == null || director == null || writer==null || genres==null || country==null || language==null || releaseDate==null || cast==null || runTime==null) {
			throw new IllegalArgumentException("All the fields should be filled except description.");
		}
		
		try (final Connection conn = this.getConnection()) {
			final String query = "UPDATE people SET Title=?, Director=?, Writer=?, Genres=?, Country=?, Language=?, ReleaseDate=?, Cast=?,RunTime=?, Description=? WHERE id=?";
			
			try (PreparedStatement statement = conn.prepareStatement(query)) {
				statement.setString(1, title);
				statement.setString(2, director);
				statement.setString(3, writer);
				statement.setString(4, genres);
				statement.setString(5, country);
				statement.setString(6, language);
				statement.setDate(7, releaseDate);
				statement.setString(8, cast);
				statement.setString(9, runTime);
				statement.setString(10, description);
				statement.setInt(11, id);
				
				if (statement.executeUpdate() == 1) {
					return new Movie(id, title,director,writer,genres,country,language,releaseDate,cast,runTime,description); 
				} else {
					throw new IllegalArgumentException("All the fields should be filled except description.");
				}
			}
		} catch (SQLException e) {
			throw new DAOException();
		}
	}
	
	public Movie add(String title, String director, String writer, String genres, String country, String language,
			Date releaseDate, String cast, String runTime, String description)
	throws DAOException, IllegalArgumentException {
		if (title==null || director==null || writer==null || genres==null || country==null || language==null || releaseDate==null || cast==null || runTime==null) {
			throw new IllegalArgumentException("All the fields should be filled except description.");
		}
		
		try (final Connection conn = this.getConnection()) {
			final String query = "INSERT INTO people VALUES(null, ?, ?)";
			
			try (PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, title);
				statement.setString(2, director);
				statement.setString(3, writer);
				statement.setString(4, genres);
				statement.setString(5, country);
				statement.setString(6, language);
				statement.setDate(7, releaseDate);
				statement.setString(8, cast);
				statement.setString(9, runTime);
				statement.setString(10, description);
				
				if (statement.executeUpdate() == 1) {
					try (ResultSet resultKeys = statement.getGeneratedKeys()) {
						if (resultKeys.next()) {
							return new Movie(resultKeys.getInt(1), title, director, writer, genres, country, language, releaseDate,cast,runTime,description);
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
