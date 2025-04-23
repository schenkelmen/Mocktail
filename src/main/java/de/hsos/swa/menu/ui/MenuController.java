package de.hsos.swa.menu.ui;

import de.hsos.swa.suchen.ui.start.StartControl;
import de.hsos.swa.verwalten.ui.verwalten.VerwaltenControl;

public class MenuController {
    private MenuView menuView = new MenuView();
    private StartControl startControl = new StartControl();
    private VerwaltenControl verwaltenControl = new VerwaltenControl();

    public void start() {
        boolean running = true;

        while (running) {
            int auswahl = menuView.zeigeMenue();

            switch (auswahl) {
                case 1:
                    startControl.starteSuche();
                    break;
                case 2:
                    verwaltenControl.starteVerwaltung();
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
