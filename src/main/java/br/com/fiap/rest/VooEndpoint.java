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

import br.com.fiap.dao.impl.VooDao;
import br.com.fiap.model.Voo;

@Path("/voos")

public class VooEndpoint {
	private VooDao dao = new VooDao();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Voo> getAll() {
		return dao.getAll();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response create(Voo voo) {
		if(voo == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		try {
			dao.save(voo);
			return Response.status(Response.Status.CREATED).entity(voo).build();
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
		Voo voo = dao.findById(id);
		if (voo == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.status(Response.Status.OK).entity(voo).build();
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response update(@PathParam("id") Long id, Voo voo) {
		if (id == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (voo == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (dao.findById(id) == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		voo.setId(id);
		try {
			dao.update(voo);
			return Response.status(Response.Status.OK).entity(voo).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response delete(@PathParam("id") Long id, Voo voo) {
		if (id == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (voo == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (dao.findById(id) == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		voo.setId(id);
		try {
			dao.delete(voo);
			return Response.status(Response.Status.OK).entity(voo).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}	
