package de.hsos.swa.menu.boundary;

import de.hsos.swa.menu.control.CocktailService;
import de.hsos.swa.menu.entity.Drink;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/drinks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DrinkResource {

    @Inject
    CocktailService cocktailService;

    @GET
    @Path("/search")
    public Response searchDrinks(@QueryParam("type") String type,
                                 @QueryParam("name") String name,
                                 @QueryParam("ingredient") String ingredient,
                                 @QueryParam("category") String category) {

        List<Drink> result = cocktailService.search(type, name, ingredient, category);
        return Response.ok(result).build();
    }
}
