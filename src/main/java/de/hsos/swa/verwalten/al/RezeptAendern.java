package de.hsos.swa.verwalten.al;

import de.hsos.swa.shared.Rezept;

public interface RezeptAendern {
    public void rezeptAendern(long id, String name, String zutaten, String zubereitung);
}
