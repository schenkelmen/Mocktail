package de.hsos.swa.suchen.ui.detail;

public class DetailControl {
    public final DetailView detailView = new DetailView();

    public void starteDetailAnsicht() {
        long eingabe = detailView.frageNachMocktailId();

        zeigeDetails(eingabe);
    }

    private void zeigeDetails(long eingabe) {
        detailView.zeigeDetails(eingabe);
    }
}
