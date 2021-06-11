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

import br.com.fiap.dao.impl.DestinoDao;
import br.com.fiap.model.Destino;

@Path("/destinos")
public class DestinoEndpoint {
	private DestinoDao dao = new DestinoDao();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Destino> getAll() {
		return dao.getAll();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response create(Destino destino) {
		if(destino == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		try {
			dao.save(destino);
			return Response.status(Response.Status.CREATED).entity(destino).build();
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
		Destino destino = dao.findById(id);
		if (destino == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.status(Response.Status.OK).entity(destino).build();
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response update(@PathParam("id") Long id, Destino destino) {
		if (id == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (destino == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (dao.findById(id) == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		destino.setId(id);
		try {
			dao.update(destino);
			return Response.status(Response.Status.OK).entity(destino).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response delete(@PathParam("id") Long id, Destino destino) {
		if (id == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (destino == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (dao.findById(id) == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		destino.setId(id);
		try {
			dao.delete(destino);
			return Response.status(Response.Status.OK).entity(destino).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}	
