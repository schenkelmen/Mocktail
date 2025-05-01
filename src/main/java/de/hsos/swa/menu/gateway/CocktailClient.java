package de.hsos.swa.menu.gateway;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.*;

@RegisterRestClient(configKey = "cocktail-api")
@Path("/api/json/v1/1")
public interface CocktailClient {

    @GET
    @Path("/filter.php")
    CocktailResponse searchByCategory(@QueryParam("c") String category);

    @GET
    @Path("/search.php")
    CocktailResponse searchByName(@QueryParam("s") String name);

    @GET
    @Path("/lookup.php")
    CocktailResponse lookupById(@QueryParam("i") String id);

    @GET
    @Path("/filter.php")
    CocktailResponse searchByIngredient(@QueryParam("i") String ingredient);
}

