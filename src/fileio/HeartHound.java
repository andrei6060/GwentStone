package fileio;


import java.util.ArrayList;

public final class HeartHound extends Environment {
    private final int trei = 3;
    public HeartHound(final Environment cardInput) {
        super(cardInput);
    }

    @Override
    public Table ability(final Table table, final int nr) {
        ArrayList<Card> row2 = new ArrayList<>();
        int max = 0;
        int index = 0;
        int indexmax = 0;
        for (Card card : table.table.get(nr)) {
            index++;
            Minion minion = new Minion((Minion) card);
            if (minion.getHealth() > max) {
                indexmax = index;
            }
        }
        Minion minion2 = new Minion((Minion) table.table.get(nr).get(indexmax));
        table.table.get(nr).remove(indexmax);
        table.table.get(trei - nr).add(minion2);

        table.table();

        return table;
    }
}
