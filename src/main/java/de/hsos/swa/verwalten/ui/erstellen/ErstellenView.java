package de.hsos.swa.verwalten.ui.erstellen;

import java.util.Scanner;

public class ErstellenView {
    private Scanner scanner = new Scanner(System.in);

    public String frageNachName() {
        System.out.println("Geben Sie Den Namen des Mocktails an.");
        System.out.print("Bitte Eingeben: ");
        return scanner.nextLine();
    }

    public String frageNachRezept() {
        System.out.println("Geben Sie das Rezept des Mocktails an.");
        System.out.print("Bitte Eingeben: ");
        return scanner.nextLine();
    }

    public void bestaetigung(String name, String rezept) {
        System.out.println("Mocktail: " + name);
        System.out.println("Rezept:   " + rezept);
        System.out.println("HINZUGEFÜGT!");
    }
}
