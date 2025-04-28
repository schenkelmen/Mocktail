package de.hsos.swa.menu.boundary.util.dto;

import jakarta.json.bind.annotation.JsonbCreator;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public record MocktailnummerDTO(@Schema(description = "Eindeutige ID des neu erstellten Mocktails") String id) {
    @JsonbCreator
    public MocktailnummerDTO {}

}
