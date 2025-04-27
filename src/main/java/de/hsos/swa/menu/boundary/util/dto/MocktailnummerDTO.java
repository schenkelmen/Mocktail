package de.hsos.swa.menu.boundary.util.dto;

import jakarta.json.bind.annotation.JsonbCreator;

public record MocktailnummerDTO(String id) {
    @JsonbCreator
    public MocktailnummerDTO {}

}
