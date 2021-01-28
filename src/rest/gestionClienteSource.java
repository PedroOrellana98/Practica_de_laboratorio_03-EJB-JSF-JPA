package rest;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ejb.PersonaEJB;
import entidad.Persona;

@Path("/cliente/")

public class gestionClienteSource {
	@EJB
	PersonaEJB persona;

	@POST

	@Path("/iniciarsesión/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)

	public Response getCliente(@FormParam("cedula") String cedula, @FormParam("password") String password)
			throws IOException {

		if (persona.verificarUsuario(cedula, password)) {

			return Response.ok("Logueado satisfactoriamente").build();

		} else {

			return Response.ok("Logueo falló").build();

		}
	}

	@POST

	@Path("/registrar/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)

	public Response getRegistro(@FormParam("cedula") String cedula, @FormParam("correo") String correo,
			@FormParam("password") String password) throws IOException {

		Persona p = persona.find(cedula);
		if (p != null) {

			p.setCorreo(correo);
			p.setPassword(password);

			try {
				persona.edit(p);
				return Response.ok("Cliente Registrado").build();
			} catch (Exception e) {

				return Response.ok("Cliente no Registrado").build();
			}
		} else {

			return Response.ok("Cliente no Registrado").build();

		}
	}
	
	@PUT

	@Path("/actualizar/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public Response getActualizar(@FormParam("cedula")String cedula, @FormParam("nombre")String nombre, @FormParam("apellido")String apellido, 
			@FormParam("telefono")String telefono, @FormParam("direccion")String direccion, @FormParam("correo")String correo, @FormParam("password")String password)
	throws IOException{
		
		try {
			
			Persona p = persona.find(cedula);
			p.setNombre(nombre);
			p.setApellido(apellido);
			p.setTelefono(telefono);
			p.setDireccion(direccion);
			p.setCorreo(correo);
			p.setPassword(password);
			
			persona.edit(p);
			
			return Response.ok("Cliente Actualizado").build();		

			
		}catch(Exception e) {
			
			return Response.ok("Cliente NO Actualizado").build();		
		}
	}
	
	@PUT

	@Path("/anular/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public Response getAnular(@FormParam("cedula")String cedula)throws IOException{
		
		try {
			
			Persona p = persona.find(cedula);
			p.setAnulado('A');
			persona.edit(p);
			
			return Response.ok("Persona anulada").build();
		}catch(Exception e) {
			return Response.ok("Persona NO anulada").build();
		}
	}
}