package de.hsos.swa.verwalten.ui.erstellen;

import de.hsos.swa.shared.Rezept;
import de.hsos.swa.suchen.al.SucheRezept;
import de.hsos.swa.verwalten.al.RezeptErstellen;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ErstellenControl {

    @Inject
    ErstellenView erstellenView;

    @Inject
    RezeptErstellen user;

    public void starteErstellungAnsicht() {
        String name = erstellenView.frageNachName();
        String zutaten = erstellenView.frageNachZutaten();
        String zubereitung = erstellenView.frageNachZubereitung();

        erstelleMocktail(name, zutaten, zubereitung);
    }

    private void erstelleMocktail(String name, String zutaten, String zubereitung) {
        user.rezeptErstellen(name, zutaten, zubereitung);
        erstellenView.bestaetigung(name, zutaten, zubereitung);
    }
}
