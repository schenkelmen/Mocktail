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
import org.jboss.logging.Logger;

import java.util.Optional;

@Path("mocktails/{id}")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RunOnVirtualThread
public class MocktailResource {

    private static final Logger LOG = Logger.getLogger(MocktailResource.class);

    @Inject
    MocktailVerwalter mocktailVerwalter;

    @GET
    public Response abfragenMocktailMitId(@PathParam("id") String id) {
        LOG.info("GET Anfrage: Abfrage Mocktail mit ID: " + id);
        Optional<Mocktail> optionalMocktail = this.mocktailVerwalter.findeMocktailMitId(id);
        if(optionalMocktail == null || optionalMocktail.isEmpty()) {
            LOG.warn("Mocktail mit ID " + id + " nicht gefunden.");
            return Response.status(Status.NO_CONTENT).build();
        }
        LOG.info("Mocktail gefunden: " + id);
        return Response.ok().entity(MocktailDTO.toDTO(optionalMocktail.get())).build();
    }

    @POST
    public Response postResponse() {
        LOG.warn("POST Anfrage auf mocktails/{id} - nicht erlaubt.");
        return Response.status(Status.METHOD_NOT_ALLOWED).build();
    }

    @DELETE
    public Response anweisenLoeschungEinesMocktails(@PathParam("id") String id) {
        LOG.info("DELETE Anfrage: Mocktail löschen mit ID: " + id);
        boolean check = this.mocktailVerwalter.entfernen(id);
        if(check) {
            LOG.info("Mocktail erfolgreich gelöscht: " + id);
            return Response.ok().build();
        } else {
            LOG.warn("Mocktail konnte nicht gelöscht werden: " + id);
            return Response.status(Status.NOT_MODIFIED).build();
        }
    }

    @PATCH
    @Path("/zutaten")
    public Response anweisenAenderungZutaten(@PathParam("id") String id, @QueryParam("zutaten") String zutaten) {
        LOG.info("PATCH Anfrage: Änderung Zutaten für Mocktail ID: " + id);
        try {
            Optional<Mocktail> optionalMocktail = this.mocktailVerwalter.aendereZutaten(id, zutaten);
            if (optionalMocktail == null || optionalMocktail.isEmpty()) {
                LOG.warn("Mocktail nicht gefunden zur Änderung der Zutaten: " + id);
                return Response.status(Status.NOT_FOUND).build();
            }
            LOG.info("Zutaten erfolgreich geändert für Mocktail: " + id);
            return Response.ok().build();
        } catch (IllegalArgumentException ie) {
            LOG.error("Fehler beim Ändern der Zutaten für Mocktail: " + id, ie);
            ErrorMessage errorMessage = new ErrorMessage(12,
                    "Zutaten wurden nicht geändert.",
                    "Mocktail ID: " + id);
            return Response.status(Status.BAD_REQUEST).entity(errorMessage).build();
        }
    }

    @PATCH
    @Path("/zubereitung")
    public Response anweisenAenderungZubereitung(@PathParam("id") String id, @QueryParam("zubereitung") String zubereitung) {
        LOG.info("PATCH Anfrage: Änderung Zubereitung für Mocktail ID: " + id);
        try {
            Optional<Mocktail> optionalMocktail = this.mocktailVerwalter.aendereZubereitung(id, zubereitung);
            if (optionalMocktail == null || optionalMocktail.isEmpty()) {
                LOG.warn("Mocktail nicht gefunden zur Änderung der Zubereitung: " + id);
                return Response.status(Status.NOT_FOUND).build();
            }
            LOG.info("Zubereitung erfolgreich geändert für Mocktail: " + id);
            return Response.ok().build();
        } catch (IllegalArgumentException ie) {
            LOG.error("Fehler beim Ändern der Zubereitung für Mocktail: " + id, ie);
            ErrorMessage errorMessage = new ErrorMessage(12,
                    "Zubereitung wurde nicht geändert.",
                    "Mocktail ID: " + id);
            return Response.status(Status.BAD_REQUEST).entity(errorMessage).build();
        }
    }
}
