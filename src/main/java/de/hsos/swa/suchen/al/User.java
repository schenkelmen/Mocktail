package de.hsos.swa.suchen.al;

import de.hsos.swa.shared.Rezept;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class User implements SucheRezept, ZeigeDetailsAn {

    private String name;
    private String vorname;

    @Inject
    RezeptSuchenService rezeptSuchenService;

    @Override
    public List<Rezept> sucheRezept(String namen) {
        return rezeptSuchenService.sucheRezept(namen);
    }

    public List<Rezept> zeigeDetailsAn() {
        return rezeptSuchenService.alleRezepte();
    }

    @Override
    public Rezept zeigeDetailsAn(long rezeptnummer) {
        return rezeptSuchenService.rezeptRestClient.findById(rezeptnummer);
    }
}
