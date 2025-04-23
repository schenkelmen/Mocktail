package de.hsos.swa.suchen.ui.start;

import de.hsos.swa.suchen.ui.detail.DetailControl;
import de.hsos.swa.suchen.ui.such.SuchControl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class StartControl {
    @Inject
    StartView startView;
    @Inject
    SuchControl suchControl;
    @Inject
    DetailControl detailControl;

    public void starteAnsichtDerSuche() {
        int auswahl = startView.frageObSucheGestartetWerdenSoll();

        switch (auswahl) {
            case 1:
                suchControl.starteAnsichtDerSuche();
                break;
            case 2:
                detailControl.starteDetailAnsicht();
                break;
            case 0:
                System.out.println("Verlasse Suche");
                break;
            default:
                System.out.println("Ungültige Eingabe. Bitte erneut versuchen.");
                starteAnsichtDerSuche();
        }
    }
}
