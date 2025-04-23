package de.hsos.swa.verwalten.ui.start;

import java.util.Scanner;

public class StartVerwaltenView {
    private Scanner scanner = new Scanner(System.in);

    public int frageNachAktion() {
        int eingabe = -1;
        boolean gueltig = false;

        while (!gueltig) {
            System.out.println("Wilkommen in der Mocktail-Verwaltung");
            System.out.println("1. Hinzufügen");
            System.out.println("2. Ändern");
            System.out.println("3. Löschen");
            System.out.println("0. Verwaltung verlassen");
            System.out.print("Bitte wählen: ");

            if (scanner.hasNextInt()) {
                eingabe = scanner.nextInt();
                gueltig = true;
            } else {
                System.out.println("Ungültige Eingabe. Bitte eine Zahl eingeben.");
                scanner.next();
            }
        }

        return eingabe;
    }
}
