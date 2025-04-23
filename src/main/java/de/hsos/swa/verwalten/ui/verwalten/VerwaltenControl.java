package de.hsos.swa.verwalten.ui.verwalten;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class VerwaltenControl {

    @Inject
    VerwaltenView verwaltenView;

    public void starteVerwaltungsAnsicht() {
        long id = verwaltenView.frageNachId();

        mocktailLoeschen(id);
    }

    private void mocktailLoeschen(long id) {
        // ToDo: Löschen des Mocktails nach ID mit DELETE
        verwaltenView.bestaetigung(id);
    }
}
