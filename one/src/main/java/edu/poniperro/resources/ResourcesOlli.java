package edu.poniperro.resources;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.poniperro.resources.entities.Usuaria;
import edu.poniperro.resources.service.SerivceOlli;

@Path("/")
public class ResourcesOlli {

    @Inject
    SerivceOlli service;

    @GET
    @Path("wellcome")
    @Produces(MediaType.TEXT_PLAIN)
    public String app() {
        return "Wellcome Ollivanders!";
    }

    @GET
    @Path("usuaria/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersona(@PathParam("nombre") String nombre) {
        Usuaria usuaria = service.cargaUsuaria(nombre);
        return usuaria.getNombre().equals("") ? Response.status(404).build()
                : Response.ok(usuaria).build();

    }
}