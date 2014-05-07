package es.uvigo.esei.daa.entities;

import java.sql.Date;

public class Movie {
	private int id;
	private String title;
	private String director;
	private String writer;
	private String genres;
	private String country;
	private String language;
	private Date releaseDate;
	private String cast;
	private String runTime;
	private String description;
	
	public Movie() {
	}
	
	public Movie(int id, String title, String director, String writer, String genres, String country, String language, Date releaseDate, String cast,
			String runTime, String description) {
		
		this.id = id;
		this.title=title;
		this.director=director;
		this.writer=writer;
		this.genres=genres;
		this.country=country;
		this.language=language;
		this.releaseDate=releaseDate;
		this.cast=cast;
		this.runTime=runTime;
		this.description=description;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}
	
	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleasedate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public String getRunTime() {
		return runTime;
	}

	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
