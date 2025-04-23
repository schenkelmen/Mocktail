package de.hsos.swa.suchen.ui.start;

import java.util.Scanner;

public class StartView {
    private final Scanner scanner = new Scanner(System.in);

    public int frageObSucheGestartetWerdenSoll() {
        int eingabe = -1;
        boolean gueltig = false;

        while (!gueltig) {
            System.out.println("Willkommen zur Mocktailsuche!");
            System.out.println("Möchten Sie ein Mocktail suchen oder anzeigen lassen?");
            System.out.println("1. Suchen");
            System.out.println("2. Anzeigen");
            System.out.println("0. Suche verlassen");
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
