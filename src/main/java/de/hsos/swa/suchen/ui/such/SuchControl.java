package de.hsos.swa.suchen.ui.such;

public class SuchControl {
    private final SuchView suchView = new SuchView();

    public void starteAnsichtDerSuche() {
        String eingabe = suchView.frageNachSuchbegriff();

        zeigeErgebnisse(eingabe);
    }

    private void zeigeErgebnisse(String eingabe) {
        // ToDo: Restliche Suchlogik mit Ausgabe
        suchView.zeigeErgebnisse(eingabe);
    }
}
