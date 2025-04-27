package de.hsos.swa.menu.entity;

import java.util.Collection;
import java.util.Optional;

public interface MocktailVerwalter {
    String anlegenNeuMocktail(String name, String zutaten, String zubereitung);
    boolean entfernen(String id);
    Optional<Mocktail> aendereZutaten(String id, String zutaten);
    Optional<Mocktail> aendereZubereitung(String id, String zubereitung);
    Optional<Mocktail> findeMocktailMitId(String id);
    Collection<Mocktail> alleMocktails();
}
