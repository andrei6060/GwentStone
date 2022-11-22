package fileio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties({ "hasAttacked", "frozen", "frozencount" })
public class Hero extends Card {

    private final int healthinitial = 30;
    private boolean hasAttacked = false;
    private int health = healthinitial;
    public Hero(final CardInput card) {
        this.setName(card.getName());
        this.setColors(card.getColors());
        this.setDescription(card.getDescription());
        this.setMana(card.getMana());
    }
    public Hero(final Hero card) {
        this.setName(card.getName());
        this.setColors(card.getColors());
        this.setDescription(card.getDescription());
        this.setMana(card.getMana());
        this.setHealth(card.getHealth());
        this.setHasAttacked(card.isHasAttacked());
    }

    public final boolean isHasAttacked() {
        return hasAttacked;
    }

    public final void setHasAttacked(final boolean hasAttacked) {
        this.hasAttacked = hasAttacked;
    }

    public final int getHealth() {
        return health;
    }

    public final void setHealth(final int health) {
        this.health = health;
    }
}
