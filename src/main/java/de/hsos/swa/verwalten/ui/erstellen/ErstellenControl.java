package de.hsos.swa.verwalten.ui.erstellen;

public class ErstellenControl {
    private ErstellenView erstellenView = new ErstellenView();

    public void starteErstellungAnsicht() {
        String name = erstellenView.frageNachName();
        String rezept = erstellenView.frageNachRezept();

        erstelleMocktail(name, rezept);
    }

    private void erstelleMocktail(String name, String rezept) {
        // ToDo: Erstellen des Mockteils und CREATE-Aufruf
        erstellenView.bestaetigung(name, rezept);
    }
}
