package fileio;


import java.util.ArrayList;

public final class Firestorm extends Environment {
    private final int trei = 3;
    public Firestorm(final Environment cardInput) {
        super(cardInput);
    }

    @Override
    public Table ability(final Table table, final int nr) {
        ArrayList<Card> row2 = new ArrayList<>();
        for (Card card: table.getTable().get(nr)) {
                    Minion minion2 = new Minion((Minion) card);
                    minion2.setHealth(minion2.getHealth() - 1);
                    if (minion2.getHealth() > 0) {
                        row2.add(minion2);
            }
        }
        if (nr == 0) {
            table.setRow0(row2);
        } else if (nr == 1) {
            table.setRow1(row2);
        } else if (nr == 2) {
            table.setRow2(row2);
        } else if (nr == trei) {
            table.setRow3(row2);
        }
        table.table();
        return table;
    }
}

