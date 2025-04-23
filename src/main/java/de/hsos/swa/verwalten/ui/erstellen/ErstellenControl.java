package de.hsos.swa.verwalten.ui.erstellen;

public class ErstellenControl {
    private ErstellenView erstellenView = new ErstellenView();

    public void starteErstellungAnsicht() {
        String name = erstellenView.frageNachName();
        String zutaten = erstellenView.frageNachZutaten();
        String zubereitung = erstellenView.frageNachZubereitung();

        erstelleMocktail(name, zutaten, zubereitung);
    }

    private void erstelleMocktail(String name, String rezept, String zubereitung) {
        // ToDo: Erstellen des Mockteils und CREATE-Aufruf
        erstellenView.bestaetigung(name, rezept, zubereitung);
    }
}
