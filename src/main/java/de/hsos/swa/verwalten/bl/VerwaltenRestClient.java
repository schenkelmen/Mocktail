package de.hsos.swa.verwalten.bl;

import de.hsos.swa.shared.Rezept;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient
@Path("/rezepte")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface VerwaltenRestClient {

    @POST
    void create(String name, String zutaten, String zubereitung);

    @DELETE
    @Path("/{id}")
    void delete(@PathParam("id") Long id);

    @PUT
    @Path("/{id}")
    void update(@PathParam("id") Long id, String name, String zutaten, String zubereitung);

}
