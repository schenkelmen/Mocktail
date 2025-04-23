package de.hsos.swa.menu.ui;

import de.hsos.swa.suchen.ui.start.StartControl;
import de.hsos.swa.verwalten.ui.start.StartVerwaltenControl;

public class MenuController {
    private MenuView menuView = new MenuView();
    private StartControl startControl = new StartControl();
    private StartVerwaltenControl startVerwaltenControl = new StartVerwaltenControl();

    public void start() {
        boolean running = true;

        while (running) {
            int auswahl = menuView.zeigeMenue();

            switch (auswahl) {
                case 1:
                    startControl.starteAnsichtDerSuche();
                    break;
                case 2:
                    startVerwaltenControl.starteVerwaltung();
                    break;
                case 0:
                    running = false;
                    System.out.println("Programm wird beendet.");
                    break;
                default:
                    System.out.println("Ungültige Eingabe. Bitte erneut versuchen.");
            }
        }
    }
}
