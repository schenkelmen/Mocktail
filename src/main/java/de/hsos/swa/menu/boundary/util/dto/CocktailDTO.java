package de.hsos.swa.menu.boundary.util.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CocktailDTO(
        @JsonProperty("idDrink") String idDrink,                   // 👈 NEU
        @JsonProperty("strDrink") String strDrink,
        @JsonProperty("strInstructions") String strInstructions,
        @JsonProperty("strIngredient1") String strIngredient1,
        @JsonProperty("strIngredient2") String strIngredient2,
        @JsonProperty("strIngredient3") String strIngredient3
) {
    public String strIngredientsAsString() {
        return String.join(", ",
                strIngredient1 != null ? strIngredient1 : "",
                strIngredient2 != null ? strIngredient2 : "",
                strIngredient3 != null ? strIngredient3 : ""
        ).replaceAll(",\\s*$", "");
    }
}

