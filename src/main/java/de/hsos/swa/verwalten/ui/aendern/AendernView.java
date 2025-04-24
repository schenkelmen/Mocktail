package de.hsos.swa.verwalten.ui.aendern;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Scanner;

@ApplicationScoped
public class AendernView {
    private Scanner scanner = new Scanner(System.in);

    public long frageNachId() {
        long eingabe = -1;
        boolean gueltig = false;

        while (!gueltig) {
            System.out.println("Geben Sie die ID des zu ändernden Mocktails ein.");
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

    public String nameAendern() {
        System.out.println("Wenn keine Änderung gewünscht, Feld frei lassen.");
        System.out.print("Geben sie einen neuen Namen ein: ");
        return scanner.nextLine();
    }

    public String zutatenAendern() {
        System.out.print("Geben sie die neuen Zutaten ein: ");
        return scanner.nextLine();
    }

    public String zubereitungAendern() {
        System.out.print("Geben sie die neue Zubereitung ein: ");
        return scanner.nextLine();
    }

    public void bestaetigung(String name, String zutaten, String zubereitung) {
        System.out.println("Mocktail:    " + name);
        System.out.println("Zutaten:     " + zutaten);
        System.out.println("Zubereitung: " + zubereitung);
        System.out.println("GEÄNDERT!");
    }

    public void zeigeFehler(String s) {
        System.out.println("Fehler: " + s);
    }
}
