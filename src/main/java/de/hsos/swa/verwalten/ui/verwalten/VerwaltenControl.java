package de.hsos.swa.verwalten.ui.verwalten;

public class VerwaltenControl {
    private VerwaltenView verwaltenView = new VerwaltenView();

    public void starteVerwaltungsAnsicht() {
        long id = verwaltenView.frageNachId();

        mocktailLoeschen(id);
    }

    private void mocktailLoeschen(long id) {
        // ToDo: Löschen des Mocktails nach ID mit DELETE
        verwaltenView.bestaetigung(id);
    }
}
