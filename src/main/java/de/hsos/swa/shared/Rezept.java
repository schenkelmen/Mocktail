package de.hsos.swa.shared;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Rezept {
    private long id;
    private String name;
    private List<Zutat> zutaten = new ArrayList<>();

    public Rezept(long id, String name) {
        this.id = id;
        Objects.requireNonNull(name);
        this.name = name;
    }

    public Rezept(long id, String name, List<Zutat> zutaten) {
        this.id = id;
        this.name = Objects.requireNonNull(name);
        this.zutaten = Objects.requireNonNull(zutaten);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Zutat> getZutaten() {
        return zutaten;
    }

    public void setZutaten(List<Zutat> zutaten) {
        this.zutaten = zutaten != null ? zutaten : new ArrayList<>();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Rezept)) {
            return false;
        }
        Rezept other = (Rezept) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Rezept [id=" + this.id + ", name=" + this.name
                + ", Zutaten: " + this.zutaten.toString() + "]";
    }

}
