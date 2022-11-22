package fileio;

import java.util.ArrayList;

public final class GeneralKocioraw extends Hero {
    private final int trei = 3;
    public GeneralKocioraw(final Hero card) {
        super(card);
    }
    /**
     * Functia ce implementeaza abilitatea eroului
     */
    public Table bloodthirst(final Table table, final int nr) {
        ArrayList<Card> row2 = new ArrayList<>();
        for (Card card: table.table.get(nr)) {
            Minion minion2 = new Minion((Minion) card);
            minion2.setAttackDamage(minion2.getAttackDamage() + 1);
            row2.add(minion2);
        }
        if (nr == 0) {
            table.row0 = row2;
        } else if (nr == 1) {
            table.row1 = row2;
        } else if (nr == 2) {
            table.row2 = row2;
        } else if (nr == trei) {
            table.row3 = row2;
        }
        table.table();
        return table;
    }
}
