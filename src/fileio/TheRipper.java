package fileio;

import java.util.ArrayList;

public final class TheRipper extends Minion {

    public TheRipper(final CardInput cardInput) {
        super(cardInput);
    }

    public TheRipper(final Minion cardInput) {
        super(cardInput);
    }
    /**
     * Functia ce implementeaza abilitatea Minionului
     */
    public Table weakknees(final Table table, final int x, final int y) {
        ArrayList<Card> row2 = new ArrayList<>();
        ArrayList<Integer> v = new ArrayList<>();
        ((Minion) table.table.get(x).get(y)).setAttackDamage(((Minion) table
                .table.get(x).get(y)).getAttackDamage() - 2);
        if (((Minion) table.table.get(x).get(y)).getAttackDamage() < 0) {
            ((Minion) table.table.get(x).get(y)).setAttackDamage(0);
        }

        table.table();
        return table;
    }

}
