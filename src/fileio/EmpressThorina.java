package fileio;

import java.util.ArrayList;

public final class EmpressThorina extends Hero {
    private final int trei = 3;
    public EmpressThorina(final Hero  card) {
        super(card);
    }
    /**
     * Functia ce implementeaza abilitatea eroului
     */
    public Table lowblow(final Table table, final int nr) {
            ArrayList<Card> row2 = new ArrayList<>();
            int max = 0;
            int index = 0;
            int indexmax = 0;
            for (Card card : table.table.get(nr)) {
                Minion minion2 = new Minion((Minion) card);
                if (minion2.getHealth() > max) {
                    indexmax = index;
                    max = minion2.getHealth();
                }
                index++;
                row2.add(minion2);
            }
            row2.remove(indexmax);
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
