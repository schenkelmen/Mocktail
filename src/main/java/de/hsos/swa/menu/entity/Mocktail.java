package de.hsos.swa.menu.entity;

import java.util.Objects;

public class Mocktail {
    private String id;
    private String name;
    private String zutaten;
    private String zubereitung;

    public Mocktail(String id, String name, String zutaten, String zubereitung) {
        Objects.requireNonNull(id);
        this.id = id;
        Objects.requireNonNull(name);
        this.name = name;
        Objects.requireNonNull(zutaten);
        this.zutaten = zutaten;
        Objects.requireNonNull(zubereitung);
        this.zubereitung = zubereitung;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getZutaten() {
        return zutaten;
    }

    public String getZubereitung() {
        return zubereitung;
    }
    public final void setZutaten(String zutaten) {
        Objects.requireNonNull(zutaten);
        this.zutaten = zutaten;
    }
    public final void setZubereitung(String zubereitung) {
        Objects.requireNonNull(zubereitung);
        this.zubereitung = zubereitung;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Mocktail)) {
            return false;
        }
        Mocktail other = (Mocktail) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Mocktail [id=" + this.id + ", name=" + this.name
                                          + ", zutaten: " + this.zutaten
                                          + ", zubereitung: " + this.zubereitung + "]";
    }
}
