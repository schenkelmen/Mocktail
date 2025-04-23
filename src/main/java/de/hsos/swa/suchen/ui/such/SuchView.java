package de.hsos.swa.suchen.ui.such;

import de.hsos.swa.shared.Rezept;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Scanner;

@ApplicationScoped
public class SuchView {
    private Scanner scanner = new Scanner(System.in);

    public String frageNachSuchbegriff() {
        System.out.println("Geben Sie ein Suchbegriff ein!");
        System.out.print("Bitte eingeben: ");
        return scanner.nextLine();
    }

    public void zeigeErgebnisse(String eingabe) {
        System.out.println("Eingabe: " + eingabe);
    }

    public void zeigeErgebnisse(List<Rezept> ergebnisse) {
        System.out.println("Suchergebnisse:");
        for (Rezept rezept : ergebnisse) {
            System.out.println("ID: " + rezept.getId() + ", Name: " + rezept.getName());
        }
    }
}
