package de.hsos.swa.menu.gateway;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.hsos.swa.menu.boundary.util.dto.CocktailDTO;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CocktailResponse(List<CocktailDTO> drinks) {}

