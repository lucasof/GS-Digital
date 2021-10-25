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

import br.com.fiap.dao.impl.ClienteDao;
import br.com.fiap.model.Cliente;


@Path("/clientes")
public class ClienteEndpoint {
	
	private ClienteDao dao = new ClienteDao();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> getAll() {
		return dao.getAll();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response create(Cliente cliente) {
		if(cliente == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		try {
			dao.save(cliente);
			return Response.status(Response.Status.CREATED).entity(cliente).build();
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
		Cliente cliente = dao.findById(id);
		if (cliente == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.status(Response.Status.OK).entity(cliente).build();
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response update(@PathParam("id") Long id, Cliente cliente) {
		if (id == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (cliente == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (dao.findById(id) == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		cliente.setId(id);
		try {
			dao.update(cliente);
			return Response.status(Response.Status.OK).entity(cliente).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response delete(@PathParam("id") Long id, Cliente cliente) {
		if (id == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (cliente == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (dao.findById(id) == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		cliente.setId(id);
		try {
			dao.delete(cliente);
			return Response.status(Response.Status.OK).entity(cliente).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
