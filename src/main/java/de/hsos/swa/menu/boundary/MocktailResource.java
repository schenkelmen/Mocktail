package de.hsos.swa.menu.boundary;

import de.hsos.swa.menu.boundary.util.error.ErrorMessage;
import de.hsos.swa.menu.boundary.util.dto.MocktailDTO;
import de.hsos.swa.menu.entity.Mocktail;
import de.hsos.swa.menu.entity.MocktailVerwalter;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.util.Optional;


@Path("mocktails/{id}")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RunOnVirtualThread
public class MocktailResource {

    @Inject
    MocktailVerwalter mocktailVerwalter;

    @GET
    public Response abfragenMocktailMitId(@PathParam("id") String id) {
        Optional<Mocktail> optionalMocktail = this.mocktailVerwalter.findeMocktailMitId(id);
        if(optionalMocktail == null || optionalMocktail.isEmpty()) {
            return Response.status(Status.NO_CONTENT).build();
        }
        return Response.ok().entity(MocktailDTO.toDTO(optionalMocktail.get())).build();
    }

    // @PUT
    // public Response putResponse() {
    //     return Response.status(Status.METHOD_NOT_ALLOWED).build();
    // }

    @POST
    public Response postResponse() {
        return Response.status(Status.METHOD_NOT_ALLOWED).build();
    }
        
    @DELETE
    public Response anweisenLoeschungEinesMocktails(@PathParam("id") String id) {
        boolean check = this.mocktailVerwalter.entfernen(id);
        if(check) {
            return Response.ok().build();
        } else {
            return Response.status(Status.NOT_MODIFIED).build();
        }
    }

    @PATCH
    @Path("/zutaten")
    public Response anweisenAenderungZutaten(@PathParam("id") String id, @QueryParam("zutaten") String zutaten) {
        try {
            Optional<Mocktail> optionalMocktail = this.mocktailVerwalter.aendereZutaten(id, zutaten);
            if (optionalMocktail == null || optionalMocktail.isEmpty()) {
                return Response.status(Status.NOT_FOUND).build();
            }
            return Response.ok().build();
        } catch (IllegalArgumentException ie) {
            ErrorMessage errorMessage = new ErrorMessage(12,
                    "Zutaten wurden nicht geändert.",
                    "Mocktail ID: " + id);
            return Response.status(Status.BAD_REQUEST).entity(errorMessage).build();
        }
    }

    @PATCH
    @Path("/zubereitung")
    public Response anweisenAenderungZubereitung(@PathParam("id") String id, @QueryParam("zubereitung") String zubereitung) {
        try {
            Optional<Mocktail> optionalMocktail = this.mocktailVerwalter.aendereZubereitung(id, zubereitung);
            if (optionalMocktail == null || optionalMocktail.isEmpty()) {
                return Response.status(Status.NOT_FOUND).build();
            }
            return Response.ok().build();
        } catch (IllegalArgumentException ie) {
            ErrorMessage errorMessage = new ErrorMessage(12,
                    "Zubereitung wurde nicht geändert.",
                    "Mocktail ID: " + id);
            return Response.status(Status.BAD_REQUEST).entity(errorMessage).build();
        }
    }



}
