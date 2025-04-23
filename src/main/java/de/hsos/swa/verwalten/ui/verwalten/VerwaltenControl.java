package de.hsos.swa.verwalten.ui.verwalten;

import de.hsos.swa.verwalten.al.RezeptLoeschen;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class VerwaltenControl {

    @Inject
    VerwaltenView verwaltenView;

    @Inject
    RezeptLoeschen user;

    public void starteVerwaltungsAnsicht() {
        long id = verwaltenView.frageNachId();

        mocktailLoeschen(id);
    }

    private void mocktailLoeschen(long id) {
        user.rezeptLoeschen(id);
        verwaltenView.bestaetigung(id);
    }
}
