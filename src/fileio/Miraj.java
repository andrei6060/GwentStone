package fileio;


public final class Miraj extends Minion {
    public Miraj(final Minion cardInput) {
        super(cardInput);
    }

    /**
     * Functia ce implementeaza abilitatea Minionului
     */
    public Table skyjack(final Table table, final int x, final int y, final int x2, final int y2) {
        int swap = ((Minion) table.table.get(x).get(y)).getHealth();
        int swap2 = ((Minion) table.table.get(x2).get(y2)).getHealth();
        ((Minion) table.table.get(x2).get(y2)).setHealth(swap);
        ((Minion) table.table.get(x).get(y)).setHealth(swap2);

        table.table();
        return table;
    }
}
