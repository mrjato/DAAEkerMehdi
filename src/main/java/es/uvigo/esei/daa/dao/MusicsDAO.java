package es.uvigo.esei.daa.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import es.uvigo.esei.daa.entities.Music;

public class MusicsDAO extends DAO {
	public Music get(int id)
	throws DAOException, IllegalArgumentException {
		// Get a connection with the database.
		try (final Connection conn = this.getConnection()) {
			final String query = "SELECT * FROM musics WHERE id=?";
			
			// Statement allows you to execute a query.
			try (PreparedStatement statement = conn.prepareStatement(query)) {
				// First question mark is replaced with the value of id.
				statement.setInt(1, id);
				
				// Query (statement) is executed and we get the result in ResultSet
				try (ResultSet result = statement.executeQuery()) {
					// We can iterate through the rows using .next()
					if (result.next()) {
						return new Music(
							result.getInt("id"), // We get the value of the "id" column as an integer
							result.getString("AlbumName"), // We get the value of the "name" column as a string
							result.getString("Artist"),
							result.getString("Songs"),
							result.getString("Genres"),
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
	
	public List<Music> list() throws DAOException {
		try (final Connection conn = this.getConnection()) {
			try (Statement statement = conn.createStatement()) {
				try (ResultSet result = statement.executeQuery("SELECT * FROM musics")) {
					final List<Music> musics = new LinkedList<>();
					
					while (result.next()) {
						musics.add(new Music(
							result.getInt("id"),
							result.getString("AlbumName"),
							result.getString("Artist"),
							result.getString("Songs"),
							result.getString("Genres"),
							result.getDate("ReleaseDate"),
							result.getString("Description")
						));
					}
					
					return musics;
				}
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
	public void delete(int id)
	throws DAOException, IllegalArgumentException {
		try (final Connection conn = this.getConnection()) {
			final String query = "DELETE FROM musics WHERE id=?";
			
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
	
	public Music modify(int id, String albumName, String artist,String songs, String genres,Date releaseDate, String description)
	throws DAOException, IllegalArgumentException {
		if (albumName == null || artist == null || songs == null || genres == null || releaseDate == null) {
			throw new IllegalArgumentException("All the fields should be filled except description.");
		}
		
		try (final Connection conn = this.getConnection()) {
			final String query = "UPDATE musics SET AlbumName=?, Artist=?, Songs=?, Genres=?, ReleaseDate=?, Description=? WHERE id=?";
			
			try (PreparedStatement statement = conn.prepareStatement(query)) {
				statement.setString(1, albumName);
				statement.setString(2, artist);
				statement.setString(3, songs);
				statement.setString(4, genres);
				statement.setDate(5, releaseDate);
				statement.setString(6, description);
				statement.setInt(7, id);
				
				if (statement.executeUpdate() == 1) {
					return new Music(id, albumName, artist, songs, genres, releaseDate, description); 
				} else {
					throw new IllegalArgumentException("All the fields should be filled except description.");
				}
			}
		} catch (SQLException e) {
			throw new DAOException();
		}
	}
	
	public Music add(String albumName, String artist,String songs, String genres,Date releaseDate, String description)
	throws DAOException, IllegalArgumentException {
		if (albumName == null || artist == null || songs == null || genres == null || releaseDate == null) {
			throw new IllegalArgumentException("All the fields should be filled except description.");
		}
		
		try (final Connection conn = this.getConnection()) {
			final String query = "INSERT INTO people VALUES(null, ?, ?, ?, ?, ? ,?s)";
			
			try (PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, albumName);
				statement.setString(2, artist);
				statement.setString(3, songs);
				statement.setString(4, genres);
				statement.setDate(5, releaseDate);
				statement.setString(6, description);
				
				if (statement.executeUpdate() == 1) {
					try (ResultSet resultKeys = statement.getGeneratedKeys()) {
						if (resultKeys.next()) {
							return new Music(resultKeys.getInt(1), albumName, artist,songs,genres,releaseDate,description);
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
