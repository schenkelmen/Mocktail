package de.hsos.swa.verwalten.ui.verwalten;

import java.util.Scanner;

public class VerwaltenView {
    private Scanner scanner = new Scanner(System.in);

    public long frageNachId() {
        long eingabe = -1;
        boolean gueltig = false;

        while (!gueltig) {
            System.out.println("Geben Sie die ID des zu löschenden Mocktails ein.");
            System.out.print("Bitte ID wählen: ");

            if (scanner.hasNextLong()) {
                eingabe = scanner.nextLong();
                scanner.nextLine();
                gueltig = true;
            } else {
                System.out.println("Ungültige Eingabe. Bitte eine Zahl eingeben.");
                scanner.next();
            }
        }

        return eingabe;
    }

    public void bestaetigung(long id) {
        System.out.println("Mockteil mit ID: " + id + " wurde gelöscht!");
    }
}
