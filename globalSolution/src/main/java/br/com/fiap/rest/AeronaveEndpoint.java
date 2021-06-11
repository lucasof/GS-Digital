package br.com.fiap.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.dao.impl.AeronaveDao;
import br.com.fiap.model.Aeronave;

@Path("/aeronaves")
public class AeronaveEndpoint {
	private AeronaveDao dao = new AeronaveDao();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Aeronave> getAll() {
		return dao.getAll();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response create(Aeronave aeronave) {
		if(aeronave == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		try {
			dao.save(aeronave);
			return Response.status(Response.Status.CREATED).entity(aeronave).build();
		}catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response show(@PathParam("id") Long id) {
		if (id == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		Aeronave aeronave = dao.findById(id);
		if (aeronave == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.status(Response.Status.OK).entity(aeronave).build();
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response update(@PathParam("id") Long id, Aeronave aeronave) {
		if (id == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (aeronave == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (dao.findById(id) == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		aeronave.setId(id);
		try {
			dao.update(aeronave);
			return Response.status(Response.Status.OK).entity(aeronave).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response delete(@PathParam("id") Long id, Aeronave aeronave) {
		if (id == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (aeronave == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (dao.findById(id) == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		aeronave.setId(id);
		try {
			dao.delete(aeronave);
			return Response.status(Response.Status.OK).entity(aeronave).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
