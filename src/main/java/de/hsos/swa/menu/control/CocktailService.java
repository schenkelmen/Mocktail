package de.hsos.swa.menu.control;

import de.hsos.swa.menu.entity.Drink;
import de.hsos.swa.menu.entity.MocktailVerwalter; // <--- Wichtig!
import de.hsos.swa.menu.gateway.CocktailClient;
import de.hsos.swa.menu.gateway.CocktailResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CocktailService {

    @Inject
    @RestClient
    CocktailClient cocktailClient;

    @Inject
    MocktailVerwalter mocktailVerwalter;

    public List<Drink> search(String type, String name, String ingredient, String category) {
        if ("cocktail".equalsIgnoreCase(type)) {
            CocktailResponse response = null;

            if (name != null) {
                response = cocktailClient.searchByName(name);
            } else if (ingredient != null) {
                response = cocktailClient.searchByIngredient(ingredient);
            } else if (category != null) {
                response = cocktailClient.searchByCategory(category);
            } else {
                // ⬅️ Standardfall: kein Filter -> nutze Hauptkategorie (z. B. "Ordinary Drink")
                response = cocktailClient.searchByCategory("Ordinary_Drink");
            }

            if (response != null && response.drinks() != null) {
                // Wenn du nur ein Thumbnail bekommst, musst du Details nachladen
                return response.drinks().stream()
                        .limit(10) // Begrenzung (optional)
                        .map(drink -> cocktailClient.lookupById(drink.idDrink()))
                        .filter(details -> details.drinks() != null && !details.drinks().isEmpty())
                        .map(details -> details.drinks().get(0))
                        .map(dto -> new Drink(dto.strDrink(), dto.strIngredientsAsString(), dto.strInstructions()))
                        .collect(Collectors.toList());
            }
        }
        else if ("mocktail".equalsIgnoreCase(type)) {
            return mocktailVerwalter.alleMocktails().stream()
                    .map(m -> new Drink(m.getName(), m.getZutaten(), m.getZubereitung()))
                    .collect(Collectors.toList());
        }

        return List.of(); // leer, wenn kein Typ passt
    }
}
