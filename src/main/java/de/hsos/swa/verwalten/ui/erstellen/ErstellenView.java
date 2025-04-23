package de.hsos.swa.verwalten.ui.erstellen;

import java.util.Scanner;

public class ErstellenView {
    private Scanner scanner = new Scanner(System.in);

    public String frageNachName() {
        System.out.println("Geben Sie Den Namen des Mocktails an.");
        System.out.print("Bitte Eingeben: ");
        return scanner.nextLine();
    }

    public String frageNachZutaten() {
        System.out.println("Geben Sie die Zutaten des Mocktails an.");
        System.out.print("Bitte Eingeben: ");
        return scanner.nextLine();
    }

    public String frageNachZubereitung() {
        System.out.println("Geben Sie die Zubereitung des Mocktails an.");
        System.out.print("Bitte Eingeben: ");
        return scanner.nextLine();
    }

    public void bestaetigung(String name, String zutaten, String zubereitung) {
        System.out.println("Mocktail:    " + name);
        System.out.println("Zutaten:     " + zutaten);
        System.out.println("Zubereitung: " + zubereitung);
        System.out.println("HINZUGEFÜGT!");
    }

}
