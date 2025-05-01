package de.hsos.swa.menu.entity;

public class Drink {
    private String name;
    private String zutaten;
    private String zubereitung;

    public Drink(String name, String zutaten, String zubereitung) {
        this.name = name;
        this.zutaten = zutaten;
        this.zubereitung = zubereitung;
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
}
