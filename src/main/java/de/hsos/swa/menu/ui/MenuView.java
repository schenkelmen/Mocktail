package de.hsos.swa.menu.ui;

import java.util.Scanner;

public class MenuView {
    int eingabe = -1;
    private Scanner scanner = new Scanner(System.in);

    public int zeigeMenue() {
        System.out.println("Startseite");
        System.out.println("1. Suchen");
        System.out.println("2. Verwalten");
        System.out.println("0. Beenden");
        System.out.print("Bitte wählen: ");

        if (scanner.hasNextInt()) {
            eingabe = scanner.nextInt();
        } else {
            System.out.println("Ungültige Eingabe. Bitte eine Zahl eingeben.");
            scanner.next();
        }
        return eingabe;
    }
}