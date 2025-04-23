package de.hsos.swa.suchen.ui.such;

import de.hsos.swa.shared.Rezept;
import de.hsos.swa.suchen.al.SucheRezept;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class SuchControl {

    @Inject
    SuchView suchView;

    @Inject
    SucheRezept user;

    public void starteAnsichtDerSuche() {
        String eingabe = suchView.frageNachSuchbegriff();

        zeigeErgebnisse(eingabe);
    }

    private void zeigeErgebnisse(String eingabe) {
        List<Rezept> ergebnisse = user.sucheRezept(eingabe);
        suchView.zeigeErgebnisse(ergebnisse);
    }
}
