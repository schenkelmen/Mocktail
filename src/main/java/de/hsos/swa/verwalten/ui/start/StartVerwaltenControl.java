package de.hsos.swa.verwalten.ui.start;

import de.hsos.swa.verwalten.ui.aendern.AendernControl;
import de.hsos.swa.verwalten.ui.erstellen.ErstellenControl;
import de.hsos.swa.verwalten.ui.verwalten.VerwaltenControl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class StartVerwaltenControl {
    @Inject
    StartVerwaltenView startVerwaltenView;
    @Inject
    VerwaltenControl verwaltenControl;
    @Inject
    AendernControl aendernControl;
    @Inject
    ErstellenControl erstellenControl;

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
