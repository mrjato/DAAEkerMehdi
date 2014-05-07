package es.uvigo.esei.daa.rest;

// JAX-RS => Java API for RESTful Services

import java.sql.Date;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.uvigo.esei.daa.dao.DAOException;
import es.uvigo.esei.daa.dao.MusicsDAO;

@Path("/musics")
@Produces(MediaType.APPLICATION_JSON)
public class Musics {
	private final MusicsDAO dao;
	
	public Musics() {
		this.dao = new MusicsDAO();
	}

	@GET
	public Response list() {
		try {
			// Response.ok is a HTTP 200 code =>  Everything is ok
			return Response.ok(this.dao.list(), MediaType.APPLICATION_JSON).build();
		} catch (DAOException e) {
			e.printStackTrace();
			// Response.serverError is a HTTP 500 code =>  Server error
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path("/{id}")
	public Response get(
		@PathParam("id") int id
	) {
		try {
			return Response.ok(this.dao.get(id), MediaType.APPLICATION_JSON).build();
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST)
				.entity(iae.getMessage()).build();
		} catch (DAOException e) {
			e.printStackTrace();
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("/{id}")
	public Response delete(
		@PathParam("id") int id
	) {
		try {
			this.dao.delete(id);
			
			return Response.ok(id).build();
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST)
				.entity(iae.getMessage()).build();
		} catch (DAOException e) {
			e.printStackTrace();
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
	
	@PUT
	@Path("/{id}")
	public Response modify(
		@PathParam("id") int id, 
		@FormParam("AlbumName") String albumName, 
		@FormParam("Artist") String artist,
		@FormParam("Songs") String songs, 
		@FormParam("Genres") String genres,
		@FormParam("ReleaseDate") Date releaseDate, 
		@FormParam("Description") String description
	) {
		try {
			return Response.ok(this.dao.modify(id, albumName, artist, songs, genres, releaseDate, description)).build();
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST)
				.entity(iae.getMessage()).build();
		} catch (DAOException e) {
			e.printStackTrace();
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
	
	@POST
	public Response add(
			@FormParam("AlbumName") String albumName, 
			@FormParam("Artist") String artist,
			@FormParam("Songs") String songs, 
			@FormParam("Genres") String genres,
			@FormParam("ReleaseDate") Date releaseDate, 
			@FormParam("Description") String description
	) {
		try {
			return Response.ok(this.dao.add(albumName, artist, songs, genres, releaseDate, description)).build();
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST)
				.entity(iae.getMessage()).build();
		} catch (DAOException e) {
			e.printStackTrace();
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
}
