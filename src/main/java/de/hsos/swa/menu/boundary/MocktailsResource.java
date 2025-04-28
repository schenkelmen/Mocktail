package de.hsos.swa.menu.boundary;

import de.hsos.swa.menu.boundary.util.dto.MocktailDTO;
import de.hsos.swa.menu.boundary.util.dto.MocktailnummerDTO;
import de.hsos.swa.menu.boundary.util.dto.NeuMocktailDTO;
import de.hsos.swa.menu.entity.MocktailVerwalter;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Shutdown;
import jakarta.enterprise.event.Startup;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jboss.logging.Logger;

import java.util.Collection;
import java.util.List;

@Path("mocktails")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RunOnVirtualThread
@OpenAPIDefinition(
        info = @Info(
                title = "Mocktails API",
                version = "1.0",
                description = "REST-API zur Verwaltung aller Mocktails"
        )
)
public class MocktailsResource {

    private static final Logger LOG = Logger.getLogger(MocktailsResource.class);

    @Inject
    MocktailVerwalter mocktailVerwalter;

    @ConfigProperty(name="defaultCreditLimit", defaultValue = "0.0")
    double defaultCreditLimit;

    void postConstruct(@Observes Startup event) {
        LOG.info("Startup-Event empfangen: Anlegen von Beispiel-Mocktails...");
        this.mocktailVerwalter.anlegenNeuMocktail("Flying Hirsch", "Jägermeister, RedBull", "Jägi glas ins RedBull Glas tun und dann trinken");
        this.mocktailVerwalter.anlegenNeuMocktail("Virgin Mojito", "Minze, Limette, Soda, Zuckersirup", "Minze und Limette in ein Glas geben, mit Soda auffüllen und Zuckersirup hinzufügen");
    }

    void preDestroy(@Observes Shutdown event) {
        LOG.info("Shutdown-Event empfangen: Anwendung wird beendet.");
    }

    @GET
    @Retry(maxRetries = 3, delay = 500)
    @Timeout(2000)
    @Fallback(fallbackMethod = "fallbackAbfragenAllerMocktails")
    @Operation(summary = "Alle Mocktails abrufen",
            description = "Gibt eine Liste aller verfügbaren Mocktails zurück.")
    @APIResponse(responseCode = "200", description = "Mocktails erfolgreich abgerufen",
            content = @Content(schema = @Schema(implementation = MocktailDTO.class)))
    public Collection<MocktailDTO> abfragenAllerMocktails() {
        LOG.info("GET Anfrage: Alle Mocktails werden abgefragt.");
        List<MocktailDTO> list = this.mocktailVerwalter.alleMocktails()
                .stream()
                .map(MocktailDTO::toDTO)
                .toList();
        return list;
    }

    @PATCH
    @Operation(summary = "Nicht erlaubt",
            description = "PATCH-Anfragen auf /mocktails sind nicht erlaubt.")
    @APIResponse(responseCode = "405", description = "Methode nicht erlaubt")
    public Response patchResponse() {
        LOG.warn("PATCH Anfrage auf /mocktails - Methode nicht erlaubt.");
        return Response.status(Status.METHOD_NOT_ALLOWED).build();
    }

    @POST
    @CircuitBreaker(
            requestVolumeThreshold = 5,
            failureRatio = 0.5,
            delay = 3000
    )
    @Timeout(3000)
    @Fallback(fallbackMethod = "fallbackNeuMocktailAnlegen")
    @Operation(summary = "Neuen Mocktail anlegen",
            description = "Legt einen neuen Mocktail anhand der angegebenen Daten an.")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Mocktail erfolgreich angelegt",
                    content = @Content(schema = @Schema(implementation = MocktailnummerDTO.class))),
            @APIResponse(responseCode = "400", description = "Fehlerhafte Eingabedaten")
    })
    public Response anweisenNeuMocktailAnlegen(NeuMocktailDTO neukundeDTO) {
        LOG.info("POST Anfrage: Neuer Mocktail wird angelegt. Name: " + neukundeDTO.name());
        try {
            String addedCustomer = this.mocktailVerwalter.anlegenNeuMocktail(neukundeDTO.name(), neukundeDTO.zutaten(), neukundeDTO.zubereitung());
            LOG.info("Mocktail erfolgreich angelegt mit ID: " + addedCustomer);
            return Response.status(Status.CREATED).entity(new MocktailnummerDTO(addedCustomer)).build();
        } catch (Exception e) {
            LOG.error("Fehler beim Anlegen eines neuen Mocktails.", e);
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Operation(summary = "Nicht erlaubt",
            description = "DELETE-Anfragen auf /mocktails sind nicht erlaubt.")
    @APIResponse(responseCode = "405", description = "Methode nicht erlaubt")
    public Response deleteResponse() {
        LOG.warn("DELETE Anfrage auf /mocktails - Methode nicht erlaubt.");
        return Response.status(Status.METHOD_NOT_ALLOWED).build();
    }
}
