package de.hsos.swa.verwalten.ui.aendern;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AendernControl {

    @Inject
    AendernView aendernView;

    public void starteAendernAnsicht() {
        long id = aendernView.frageNachId();

        mocktailAendern(id);
    }

    private void mocktailAendern(long id) {
        String newName = aendernView.nameAendern();
        String newZutaten = aendernView.zutatenAendern();
        String newZubereitung = aendernView.zubereitungAendern();

        // ToDo: ÄnderungsLogik mit UPDATE

        aendernView.bestaetigung(newName,  newZubereitung, newZubereitung);
    }
}
