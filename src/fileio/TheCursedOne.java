package fileio;


public final class TheCursedOne extends Minion {

    public TheCursedOne(final Minion cardInput) {
        super(cardInput);
    }

    /**
     * Functia ce implementeaza abilitatea Minionului
     */
    public Table shapeshift(final Table table, final int x, final int y) {

        int swap = ((Minion) table.table.get(x).get(y)).getAttackDamage();
        int swap2 = ((Minion) table.table.get(x).get(y)).getHealth();
        ((Minion) table.table.get(x).get(y)).setHealth(swap);
        ((Minion) table.table.get(x).get(y)).setAttackDamage(swap2);
            if (swap == 0) {
                table.table.get(x).remove(y);
            }
        table.table();
        return table;

    }
}
