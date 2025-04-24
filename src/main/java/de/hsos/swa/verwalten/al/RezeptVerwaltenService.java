package de.hsos.swa.verwalten.al;

import de.hsos.swa.shared.Rezept;
import de.hsos.swa.verwalten.bl.VerwaltenRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class RezeptVerwaltenService {

    @Inject
    @RestClient
    VerwaltenRestClient rezeptRestClient;

    public void rezeptAnlegen(String name, String zutaten, String zubereitung) {
        // Hier wird ein neues Rezept erstellt
        // und an den Rest-Client übergeben
        rezeptRestClient.create(new Rezept(0, name, zutaten, zubereitung, 0));
    }

    public void rezeptLoeschen(Long id) {
        try {
            rezeptRestClient.delete(id);
        } catch (Exception e) {
            System.out.println("Keine Mocktail mit der ID " + id + " gefunden.");
        }
    }

    public void rezeptAktualisieren(Long id, String name, String zutaten, String zubereitung) {
        // Hier wird ein Rezept aktualisiert
        // und an den Rest-Client übergeben
        Rezept rezept = new Rezept(id, name, zutaten, zubereitung, 0);
        rezeptRestClient.update(id, rezept);
    }

    public Rezept rezeptAnzeigen(Long id) {
        try {
            return rezeptRestClient.findById(id);
        } catch (Exception e) {
            System.out.println("Fehler beim Laden des Rezepts mit ID " + id + ": " + e.getMessage());
            return null;
        }
    }

}