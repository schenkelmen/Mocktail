package de.hsos.swa.menu.ui;

import java.util.Scanner;

public class MenuView {
    private Scanner scanner = new Scanner(System.in);

    public int zeigeMenue() {

        int eingabe = -1;
        boolean gueltig = false;

        while (!gueltig) {
            System.out.println("Startseite");
            System.out.println("1. Suchen");
            System.out.println("2. Verwalten");
            System.out.println("0. Beenden");
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
