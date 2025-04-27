package de.hsos.swa.menu.boundary.util.dto;

import jakarta.json.bind.annotation.JsonbCreator;

import java.util.Objects;

public record NeuMocktailDTO(String name, String zutaten, String zubereitung) {
    @JsonbCreator
    public NeuMocktailDTO {
        Objects.requireNonNull(name);
        Objects.requireNonNull(zutaten);
        Objects.requireNonNull(zubereitung);
    }
}
