package edu.poniperro.resources;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.poniperro.resources.entities.Item;
import edu.poniperro.resources.entities.Orden;
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
        return usuaria.getNombre().equals("") ? Response.status(Response.Status.NOT_FOUND).build()
                : Response.ok(usuaria).build();

    }

    @POST
    @Transactional
    @Path("ordena")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postOrden(Orden orden) {
        Orden entrada = service.comanda(orden.getUser().getNombre(), orden.getItem().getNombre());
        return entrada == null ? Response.status(Response.Status.NOT_FOUND).build()
                : Response.status(Response.Status.CREATED).entity(entrada).build();
    }

    @GET
    @Path("pedidos/{usuaria}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPeticionesUsuaria(@PathParam("usuaria") String nombre) {
        List<Orden> lista = service.cargaOrden(nombre);
        return Response.ok(lista).build();
    }

    @GET
    @Path("item/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemAtributos(@PathParam("nombre") String nombre) {
        Item item = service.cargaItem(nombre);
        return item.getNombre().equals("") ? Response.status(Response.Status.NOT_FOUND).build()
                : Response.ok(item).build();
    }

    @GET
    @Path("samu-te-quiero")
    @Produces(MediaType.TEXT_HTML)
    public String samutequiero() {
        return "<h1>Gracias Samu te quiero</h1><img src='https://i.pinimg.com/originals/b2/aa/80/b2aa807e8f2fd038e7b80d7fb80491e1.gif'/>";
    }
}