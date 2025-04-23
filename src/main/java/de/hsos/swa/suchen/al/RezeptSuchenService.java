package de.hsos.swa.suchen.al;

import de.hsos.swa.shared.Rezept;
import de.hsos.swa.suchen.bl.SuchenRestClient;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

public class RezeptSuchenService {

    @Inject
    @RestClient
    SuchenRestClient rezeptRestClient;

    public List<Rezept> sucheOnline(String name) {
        return rezeptRestClient.search(name);
    }

    public List<Rezept> alleOnline() {
        return rezeptRestClient.findAll();
    }
}