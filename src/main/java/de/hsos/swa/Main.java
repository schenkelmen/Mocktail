package de.hsos.swa;

import de.hsos.swa.menu.ui.MenuController;
import jakarta.inject.Inject;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Main implements QuarkusApplication {

    @Inject
    MenuController controller;

    @Override
    public int run(String... args) throws Exception {
        controller.start();
        return 0;
    }

    public static void main(String... args) {
        Quarkus.run(Main.class, args);
    }
}
