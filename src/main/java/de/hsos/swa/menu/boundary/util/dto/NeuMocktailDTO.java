package de.hsos.swa.menu.boundary.util.dto;

import jakarta.json.bind.annotation.JsonbCreator;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Objects;

public record NeuMocktailDTO(
        @Schema(description = "Name des neu erstellten Mocktails")
        String name,
        @Schema(description = "Zutaten des neu erstellten Mocktails")
        String zutaten,
        @Schema(description = "Zubereitung des neu erstellten Mocktails")
        String zubereitung
) {
    @JsonbCreator
    public NeuMocktailDTO {
        Objects.requireNonNull(name);
        Objects.requireNonNull(zutaten);
        Objects.requireNonNull(zubereitung);
    }
}
