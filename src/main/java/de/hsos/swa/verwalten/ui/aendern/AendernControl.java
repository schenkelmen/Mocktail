package de.hsos.swa.verwalten.ui.aendern;

import de.hsos.swa.verwalten.al.RezeptAendern;
import de.hsos.swa.shared.Rezept;
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
        Rezept rezept = user.rezeptAnzeigen(id);
        if (rezept == null) {
            aendernView.zeigeFehler("Rezept nicht gefunden!");
            return;
        }

        String newName = aendernView.nameAendern();
        String newZutaten = aendernView.zutatenAendern();
        String newZubereitung = aendernView.zubereitungAendern();

        if (!newName.isBlank()) {
            rezept.setName(newName);
        }
        if (!newZutaten.isBlank()) {
            rezept.setZutaten(newZutaten);
        }
        if (!newZubereitung.isBlank()) {
            rezept.setZubereitung(newZubereitung);
        }

        user.rezeptAendern(id, rezept.getName(), rezept.getZutaten(), rezept.getZubereitung());

        aendernView.bestaetigung(newName,  newZubereitung, newZubereitung);
    }
}
