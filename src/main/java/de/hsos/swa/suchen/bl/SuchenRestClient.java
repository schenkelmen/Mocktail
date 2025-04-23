package de.hsos.swa.suchen.bl;

import de.hsos.swa.shared.Rezept;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient
@Path("/rezepte")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface SuchenRestClient {

    @GET
    @Path("/search")
    List<Rezept> search(@QueryParam("name") String name);

    @GET
    Rezept findById(@QueryParam("id") long id);

    @GET
    List<Rezept> findAll();
}
