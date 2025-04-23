package de.hsos.swa.verwalten.ui.start;

import de.hsos.swa.verwalten.ui.aendern.AendernControl;
import de.hsos.swa.verwalten.ui.erstellen.ErstellenControl;
import de.hsos.swa.verwalten.ui.verwalten.VerwaltenControl;


public class StartVerwaltenControl {
    private StartVerwaltenView startVerwaltenView = new StartVerwaltenView();
    private VerwaltenControl verwaltenControl = new VerwaltenControl();
    private AendernControl aendernControl = new AendernControl();
    private ErstellenControl erstellenControl = new ErstellenControl();

    public void starteVerwaltung() {
        int auswahl = startVerwaltenView.frageNachAktion();

        switch (auswahl) {
            case 1:
                erstellenControl.starteErstellungAnsicht();
                break;
            case 2:
                aendernControl.starteAendernAnsicht();
                break;
            case 3:
                verwaltenControl.starteVerwaltungsAnsicht();
                break;
            case 0:
                System.out.println("Verlasse Suche");
                break;
            default:
                System.out.println("Ungültige Eingabe. Bitte erneut versuchen.");
                starteVerwaltung();
        }
    }
}
