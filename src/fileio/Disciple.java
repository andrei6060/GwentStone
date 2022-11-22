package fileio;


public class Disciple extends Minion {
    public Disciple(final Minion cardInput) {
        super(cardInput);
    }

    /**
     * Functia ce implementeaza abilitatea Minionului
     */
    public final Table godsplan(final Table table, final int x, final int y) {
        ((Minion) table.table.get(x).get(y))
                .setHealth(((Minion) table.table.get(x).get(y)).getHealth() + 2);
        table.table();
            return table;
    }
}
