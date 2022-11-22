package fileio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hasAttacked", "frozen", "frozencount" })
public class Minion extends Card {

    private boolean hasAttacked = false;
    private int attackDamage;

    private int health;
    public Minion(final CardInput cardInput) {
        this.setMana(cardInput.getMana());
        this.setColors(cardInput.getColors());
        this.setName(cardInput.getName());
        this.setDescription(cardInput.getDescription());
        this.attackDamage = cardInput.getAttackDamage();
        this.health = cardInput.getHealth();
    }

    public Minion(final Minion cardInput) {
        this.setMana(cardInput.getMana());
        this.setColors(cardInput.getColors());
        this.setName(cardInput.getName());
        this.setDescription(cardInput.getDescription());
        this.attackDamage = cardInput.getAttackDamage();
        this.health = cardInput.getHealth();
    }
    public final int getAttackDamage() {
        return attackDamage;
    }

    public final void setAttackDamage(final int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public final int getHealth() {
        return health;
    }

    public final void setHealth(final int health) {
        this.health = health;
    }

    public final boolean isHasAttacked() {
        return hasAttacked;
    }

    public final void setHasAttacked(final boolean hasAttacked) {
        this.hasAttacked = hasAttacked;
    }
}
