package es.uvigo.esei.daa.entities;

import java.sql.Date;

public class Music {
	private int id;
	private String albumName;
	private String artist;
	private String songs;
	private String genres;
	private Date releaseDate;
	private String description;
	
	public Music() {
	}
	
	public Music(int id, String albumName, String artist, String songs, String genres, Date releaseDate, String description ) {
		this.id = id;
		this.albumName=albumName;
		this.artist=artist;
		this.songs=songs;
		this.genres=genres;
		this.releaseDate=releaseDate;
		this.description=description;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setName(String albumName) {
		this.albumName = albumName;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public String getSongs() {
		return songs;
	}

	public void setSongs(String songs) {
		this.songs = songs;
	}
	
	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}
	
	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
