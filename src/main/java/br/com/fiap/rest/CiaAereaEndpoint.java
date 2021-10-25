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

import br.com.fiap.dao.impl.CiaAereaDao;
import br.com.fiap.model.CiaAerea;

@Path("/ciaAereas")
public class CiaAereaEndpoint {
private CiaAereaDao dao = new CiaAereaDao();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<CiaAerea> getAll() {
		return dao.getAll();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response create(CiaAerea ciaAerea) {
		if(ciaAerea == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		try {
			dao.save(ciaAerea);
			return Response.status(Response.Status.CREATED).entity(ciaAerea).build();
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
		CiaAerea ciaAerea = dao.findById(id);
		if (ciaAerea == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.status(Response.Status.OK).entity(ciaAerea).build();
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response update(@PathParam("id") Long id, CiaAerea ciaAerea) {
		if (id == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (ciaAerea == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (dao.findById(id) == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		ciaAerea.setId(id);
		try {
			dao.update(ciaAerea);
			return Response.status(Response.Status.OK).entity(ciaAerea).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response delete(@PathParam("id") Long id, CiaAerea ciaAerea) {
		if (id == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (ciaAerea == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (dao.findById(id) == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		ciaAerea.setId(id);
		try {
			dao.delete(ciaAerea);
			return Response.status(Response.Status.OK).entity(ciaAerea).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
