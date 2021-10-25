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

import br.com.fiap.dao.impl.PassagemDao;
import br.com.fiap.model.Passagem;

@Path("/passagens")
public class PassagemEndpoint {
	private PassagemDao dao = new PassagemDao();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Passagem> getAll() {
		return dao.getAll();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response create(Passagem passagem) {
		if(passagem == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		try {
			dao.save(passagem);
			return Response.status(Response.Status.CREATED).entity(passagem).build();
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
		Passagem passagem = dao.findById(id);
		if (passagem == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.status(Response.Status.OK).entity(passagem).build();
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response update(@PathParam("id") Long id, Passagem passagem) {
		if (id == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (passagem == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (dao.findById(id) == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		passagem.setId(id);
		try {
			dao.update(passagem);
			return Response.status(Response.Status.OK).entity(passagem).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response delete(@PathParam("id") Long id, Passagem passagem) {
		if (id == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (passagem == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (dao.findById(id) == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		passagem.setId(id);
		try {
			dao.delete(passagem);
			return Response.status(Response.Status.OK).entity(passagem).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
