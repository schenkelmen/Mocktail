package de.hsos.swa.shared;

import java.util.Objects;

public class Rezept {
    private long id;
    private String name;
    private String zutaten;
    private String zubereitung;
    private double preis;

    public Rezept() {
    }

    public Rezept(long id, String name, String zutaten, String zubereitung, double preis) {
        this.id = id;
        this.name = Objects.requireNonNull(name);
        this.zutaten = Objects.requireNonNull(zutaten);
        this.zubereitung = Objects.requireNonNull(zubereitung);
        this.preis = preis;
    }

    public long getId() {
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

    public double getPreis() {
        return preis;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setZutaten(String zutaten) {
        this.zutaten = zutaten;
    }

    public void setZubereitung(String zubereitung) {
        this.zubereitung = zubereitung;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    @Override
    public String toString() {
        return "Rezept{id=" + id + ", name='" + name + '\'' +
                ", zutaten='" + zutaten + '\'' +
                ", zubereitung='" + zubereitung + '\'' +
                ", preis=" + preis + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Rezept other)) return false;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
