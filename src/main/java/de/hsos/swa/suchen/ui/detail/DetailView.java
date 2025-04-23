package de.hsos.swa.suchen.ui.detail;

import de.hsos.swa.shared.Rezept;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Scanner;

@ApplicationScoped
public class DetailView {
    private Scanner scanner = new Scanner(System.in);

    public long frageNachMocktailId() {
        long eingabe = -1;
        boolean gueltig = false;

        while (!gueltig) {
            System.out.println("Willkommen in der Detailansicht!!");
            System.out.println("Zu Welchem Mocktail wollen Sie Details?");
            System.out.print("Bitte ID wählen: ");

            if (scanner.hasNextLong()) {
                eingabe = scanner.nextLong();
                gueltig = true;
            } else {
                System.out.println("Ungültige Eingabe. Bitte eine Zahl eingeben.");
                scanner.next();
            }
        }

        return eingabe;
    }

    public void zeigeDetails(Rezept rezept) {
        System.out.println(rezept.toString());
    }
}
