package fileio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
@JsonIgnoreProperties({ "frozen", "frozencount" })

public class Card {
    private  boolean frozen;
    private int frozencount = 0;
    private int mana;
    private String description;
    private ArrayList<String> colors = new ArrayList<>();
    private String name;

    public final int getMana() {
        return mana;
    }

    public final String getDescription() {
        return description;
    }

    public final ArrayList<String> getColors() {
        return colors;
    }

    public final String getName() {
        return name;
    }

    public final void setMana(int mana) {
        this.mana = mana;
    }

    public final void setDescription(String description) {
        this.description = description;
    }

    public final void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final boolean isFrozen() {
        return frozen;
    }

    public final void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public final void setFrozencount(int frozencount) {
        this.frozencount = frozencount;
    }

    public final int getFrozencount() {
        return frozencount;
    }
}
