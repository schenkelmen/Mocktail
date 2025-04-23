package de.hsos.swa.suchen.ui.detail;

import de.hsos.swa.shared.Rezept;
import de.hsos.swa.suchen.al.ZeigeDetailsAn;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DetailControl {
    @Inject
    DetailView detailView;

    @Inject
    ZeigeDetailsAn user;

    public void starteDetailAnsicht() {
        long eingabe = detailView.frageNachMocktailId();

        zeigeDetails(eingabe);
    }

    private void zeigeDetails(long eingabe) {
        Rezept rezept = user.zeigeDetailsAn(eingabe);
        detailView.zeigeDetails(rezept);
    }
}
