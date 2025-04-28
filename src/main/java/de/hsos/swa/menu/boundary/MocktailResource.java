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
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jboss.logging.Logger;

import java.util.Optional;

@Path("mocktail/{id}")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RunOnVirtualThread
@OpenAPIDefinition(
        info = @Info(
                title = "Mocktail API",
                version = "1.0",
                description = "REST-API zur Verwaltung einzelner Mocktails"
        )
)
public class MocktailResource {

    private static final Logger LOG = Logger.getLogger(MocktailResource.class);

    @Inject
    MocktailVerwalter mocktailVerwalter;

    @GET
    @Retry(maxRetries = 3, delay = 500)
    @Timeout(1000)
    @Fallback(fallbackMethod = "fallbackMocktailAbrufen")
    @Operation(summary = "Einen Mocktail abrufen", description = "Gibt einen Mocktail zurück.")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Mocktail erfolgreich abgerufen"),
            @APIResponse(responseCode = "500", description = "Interner Serverfehler")
    })
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

    // Ersatzmethode bei Fehler
    public Response fallbackMocktailAbrufen(String id) {
        LOG.warn("Fallback: Mocktail konnte nicht abgerufen werden: " + id);
        ErrorMessage errorMessage = new ErrorMessage(999, "Mocktail derzeit nicht verfügbar", "Mocktail ID: " + id);
        return Response.status(Status.SERVICE_UNAVAILABLE).entity(errorMessage).build();
    }

    @POST
    @Operation(summary = "Nicht erlaubt",
            description = "POST-Anfragen auf diese Ressource sind nicht erlaubt.")
    @APIResponse(responseCode = "405", description = "Methode nicht erlaubt")
    public Response postResponse() {
        LOG.warn("POST Anfrage auf mocktails/{id} - nicht erlaubt.");
        return Response.status(Status.METHOD_NOT_ALLOWED).build();
    }

    @DELETE
    @CircuitBreaker(
            requestVolumeThreshold = 4,
            failureRatio = 0.5,
            delay = 5000
    )
    @Operation(summary = "Mocktail löschen",
            description = "Löscht den Mocktail mit der angegebenen ID.")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Mocktail erfolgreich gelöscht"),
            @APIResponse(responseCode = "304", description = "Mocktail konnte nicht gelöscht werden")
    })
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
    @CircuitBreaker(
            requestVolumeThreshold = 4,
            failureRatio = 0.5,
            delay = 5000
    )
    @Operation(summary = "Zutaten eines Mocktails ändern",
            description = "Ändert die Zutatenliste eines Mocktails mit angegebener ID.")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Zutaten erfolgreich geändert"),
            @APIResponse(responseCode = "400", description = "Ungültige Eingabedaten"),
            @APIResponse(responseCode = "404", description = "Mocktail nicht gefunden")
    })
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
    @CircuitBreaker(
            requestVolumeThreshold = 4,
            failureRatio = 0.5,
            delay = 5000
    )
    @Operation(summary = "Zubereitung eines Mocktails ändern",
            description = "Ändert die Zubereitungsbeschreibung eines Mocktails mit angegebener ID.")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Zubereitung erfolgreich geändert"),
            @APIResponse(responseCode = "400", description = "Ungültige Eingabedaten"),
            @APIResponse(responseCode = "404", description = "Mocktail nicht gefunden")
    })
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
