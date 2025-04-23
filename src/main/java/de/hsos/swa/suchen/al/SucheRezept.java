package de.hsos.swa.suchen.al;

import de.hsos.swa.shared.Rezept;

import java.util.List;

public interface SucheRezept {
    public List<Rezept> sucheRezept(String namen);
}
