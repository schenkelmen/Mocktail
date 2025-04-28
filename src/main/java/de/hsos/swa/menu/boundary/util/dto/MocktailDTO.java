package de.hsos.swa.menu.boundary.util.dto;

import de.hsos.swa.menu.entity.Mocktail;
import jakarta.json.bind.annotation.JsonbCreator;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public record MocktailDTO(
        @Schema(description = "Eindeutige ID des Mocktails")
        String id,
        @Schema(description = "Name des Mocktails")
        String name,
        @Schema(description = "Zutaten des Mocktails")
        String zutaten,
        @Schema(description = "Anleitung")
        String zubereitung) {
        @JsonbCreator
        public MocktailDTO {
            if (id == null || id.isBlank()) {
                throw new IllegalArgumentException("ID must not be null or empty");
            }
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Name must not be null or empty");
            }
            if (zutaten == null || zutaten.isBlank()) {
                throw new IllegalArgumentException("Zutaten must not be null or empty");
            }
            if (zubereitung == null || zubereitung.isBlank()) {
                throw new IllegalArgumentException("Zubereitung must not be null or empty");
            }
        }
    
        public static MocktailDTO toDTO(Mocktail mocktail) {
            return new MocktailDTO(mocktail.getId(), mocktail.getName(), mocktail.getZutaten(), mocktail.getZubereitung());
        }
    
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((id == null) ? 0 : id.hashCode());
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            MocktailDTO other = (MocktailDTO) obj;
            if (id == null) {
                if (other.id != null)
                    return false;
            } else if (!id.equals(other.id))
                return false;
            return true;
        }

}
