package es.uvigo.esei.daa.entities;

import java.sql.Date;


public class Book {
	private int id;
	private String title;
	private String author;
	private String isbn;
	private String kind;
	private String editionLanguage;
	private Date releaseDate;
	private String description;
	
	public Book() {
	}
	
	public Book(int id, String title, String author, String isbn, String kind, String editionLanguage,Date releaseDate,String description) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.isbn=isbn;
		this.kind=kind;
		this.editionLanguage=editionLanguage;
		this.releaseDate=releaseDate;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getISBN() {
		return isbn;
	}

	public void setISBN(String isbn) {
		this.isbn = isbn;
	}
	
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
	
	public String getEditionLanguage() {
		return editionLanguage;
	}

	public void setEditionLanguage(String editionLanguage) {
		this.editionLanguage = editionLanguage;
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
