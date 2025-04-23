package de.hsos.swa.menu.ui;

import de.hsos.swa.suchen.ui.start.StartControl;
import de.hsos.swa.verwalten.ui.start.StartVerwaltenControl;
import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@Startup
@ApplicationScoped
public class MenuController {

    @Inject
    MenuView menuView;

    @Inject
    StartControl startControl;

    @Inject
    StartVerwaltenControl startVerwaltenControl;

    public void start() {
        boolean running = true;

        while (running) {
            int auswahl = menuView.zeigeMenue();

            switch (auswahl) {
                case 1 -> startControl.starteAnsichtDerSuche();
                case 2 -> startVerwaltenControl.starteVerwaltung();
                case 0 -> {
                    running = false;
                    System.out.println("Programm wird beendet.");
                }
                default -> System.out.println("Ungültige Eingabe. Bitte erneut versuchen.");
            }
        }
    }
}

