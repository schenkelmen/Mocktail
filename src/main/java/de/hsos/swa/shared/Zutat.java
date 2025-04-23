package de.hsos.swa.shared;

import java.util.Objects;

public class Zutat {
    private String name;
    private String menge;

    public Zutat() {}

    public Zutat(String name, String menge) {
        this.name = Objects.requireNonNull(name);
        this.menge = Objects.requireNonNull(menge);
    }

    public String getName() {
        return name;
    }

    public String getMenge() {
        return menge;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMenge(String menge) {
        this.menge = menge;
    }

    @Override
    public String toString() {
        return name + " (" + menge + ")";
    }
}
