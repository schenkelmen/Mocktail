package de.hsos.swa.suchen.ui.start;

import de.hsos.swa.suchen.ui.detail.DetailControl;
import de.hsos.swa.suchen.ui.such.SuchControl;

public class StartControl {
    private final StartView startView = new StartView();
    private final SuchControl suchControl = new SuchControl();
    private final DetailControl detailControl = new DetailControl();

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
