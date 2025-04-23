package de.hsos.swa.suchen.ui.detail;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DetailControl {
    @Inject
    DetailView detailView;

    public void starteDetailAnsicht() {
        long eingabe = detailView.frageNachMocktailId();

        zeigeDetails(eingabe);
    }

    private void zeigeDetails(long eingabe) {
        detailView.zeigeDetails(eingabe);
    }
}
