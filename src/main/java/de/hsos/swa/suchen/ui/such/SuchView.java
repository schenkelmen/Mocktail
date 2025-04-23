package de.hsos.swa.suchen.ui.such;

import java.util.Scanner;

public class SuchView {
    private Scanner scanner = new Scanner(System.in);

    public String frageNachSuchbegriff() {
        System.out.println("Geben Sie ein Suchbegriff ein!");
        System.out.print("Bitte eingeben: ");
        return scanner.nextLine();
    }

    public void zeigeErgebnisse(String eingabe) {
        // ToDo: Ausgabe einer Namensliste der Rezepte
        System.out.println("Eingabe: " + eingabe);
    }
}
