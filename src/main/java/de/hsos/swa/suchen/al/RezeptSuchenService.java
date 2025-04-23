package de.hsos.swa.suchen.al;

import de.hsos.swa.shared.Rezept;
import de.hsos.swa.suchen.bl.SuchenRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class RezeptSuchenService {

    @Inject
    @RestClient
    SuchenRestClient rezeptRestClient;

    public List<Rezept> sucheRezept(String name) {
        return rezeptRestClient.search(name);
    }

    public List<Rezept> alleRezepte() {
        return rezeptRestClient.findAll();
    }

    public Rezept rezeptDetails(long id) {
        return rezeptRestClient.findById(id);
    }
}