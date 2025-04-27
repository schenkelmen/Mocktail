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

import java.util.Collection;
import java.util.List;

@Path("mocktails")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
//@Blocking
//@NonBlocking
@RunOnVirtualThread
public class MocktailsResource {
    @Inject
    MocktailVerwalter mocktailVerwalter;

    @ConfigProperty(name="defaultCreditLimit", defaultValue = "0.0")
    double defaultCreditLimit;

    //Lifecycle methods using special CDI-Lifecyclevent
    //https://quarkus.io/guides/cdi-reference#lifecycle-events
    void postConstruct(@Observes Startup event) {
        this.mocktailVerwalter.anlegenNeuMocktail("Flying Hirsch", "Jägermeister, RedBull", "Jägi glas ins RedBull Glas tun und dann trinken");
        this.mocktailVerwalter.anlegenNeuMocktail( "Virgin Mojito", "Minze, Limette, Soda, Zuckersirup", "Minze und Limette in ein Glas geben, mit Soda auffüllen und Zuckersirup hinzufügen");
    }

    void preDestroy(@Observes Shutdown event) {
        System.out.println("CustomerResource.preDestroy");
    }
/*
    @GET
    public Multi<KundeDTO> getAllCustomers() {
        return Multi
                .createFrom()
                .items(this.kundenverwalter.getAllCustomers()
                        .stream()
                        .map(CustomerDTO::fromCustomer));
    }
*/ 

    @GET
    public Collection<MocktailDTO> abfragenAllerMocktails() {
        List<MocktailDTO> list = this.mocktailVerwalter.alleMocktails()
                                        .stream()
                                        .map(MocktailDTO::toDTO)
                                        .toList();
        return list;
        //besser: return Response.ok().entity(list).build();
        //noch besser:
            // if(list.isEmpty()) {
            //     return Response.status(Status.NOT_FOUND).build();
            // } else {
            //     return Response.ok().entity(list).build();
            // }
    }

    @PATCH //not implemented yet => the CORS-Rules defining my standard interface (and I have to implement it)
    public Response patchResponse() {
        return Response.status(Status.METHOD_NOT_ALLOWED).build();
    }


    @POST
    public Response anweisenNeuMocktailAnlegen(NeuMocktailDTO neukundeDTO) {
        try {
            String addedCustomer = this.mocktailVerwalter.anlegenNeuMocktail(neukundeDTO.name(), neukundeDTO.zutaten(), neukundeDTO.zubereitung());
            return Response.status(Status.CREATED).entity(new MocktailnummerDTO(addedCustomer)).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @DELETE //not implemented yet
    public Response deleteResponse() {
        return Response.status(Status.METHOD_NOT_ALLOWED).build();
    }
}
