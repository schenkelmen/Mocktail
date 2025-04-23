package de.hsos.swa.verwalten.al;

import de.hsos.swa.shared.Rezept;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class VerwaltenUser implements RezeptLoeschen, RezeptAendern, RezeptErstellen {

    private String name;
    private String vorname;

    @Inject
    RezeptVerwaltenService verwaltenService;

    @Override
    public void rezeptErstellen(Rezept rezept) {
        verwaltenService.rezeptAnlegen(rezept);
    }

    @Override
    public void rezeptLoeschen(long id) {
        verwaltenService.rezeptLoeschen(id);
    }

    @Override
    public void rezeptAendern(long id, Rezept rezept) {
        verwaltenService.rezeptAktualisieren(id, rezept);
    }


    // Optional: Konstruktor oder Getter/Setter, falls du name/vorname brauchst
}
