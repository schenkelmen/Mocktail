package de.hsos.swa.verwalten.ui.aendern;

import de.hsos.swa.verwalten.al.RezeptAendern;
import de.hsos.swa.verwalten.al.RezeptErstellen;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AendernControl {

    @Inject
    AendernView aendernView;

    @Inject
    RezeptAendern user;

    public void starteAendernAnsicht() {
        long id = aendernView.frageNachId();

        mocktailAendern(id);
    }

    private void mocktailAendern(long id) {
        String newName = aendernView.nameAendern();
        String newZutaten = aendernView.zutatenAendern();
        String newZubereitung = aendernView.zubereitungAendern();

        user.rezeptAendern(id, newName, newZutaten, newZubereitung);

        aendernView.bestaetigung(newName,  newZubereitung, newZubereitung);
    }
}
