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
        rezeptRestClient.create(name, zutaten, zubereitung);
    }

    public void rezeptLoeschen(Long id) {
        rezeptRestClient.delete(id);
    }

    public void rezeptAktualisieren(Long id, Rezept rezept) {
        rezeptRestClient.update(id, rezept);
    }
}
