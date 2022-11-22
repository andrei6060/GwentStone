package fileio;

import java.util.ArrayList;
import java.util.Objects;

public class Deck {
    public final ArrayList<Card> deckk = new ArrayList<>();
    public Deck(final ArrayList<CardInput> deck) {
        for (CardInput cardInput : deck) {
            if ((Objects.equals(cardInput.getName(), "Winterfell"))
                    || (Objects.equals(cardInput.getName(), "Firestorm"))
                    || (Objects.equals(cardInput.getName(), "Heart Hound"))) {
                Environment card = new Environment(cardInput);
                deckk.add(card);
            } else {
                Minion cardd = new Minion(cardInput);
                deckk.add(cardd);

            }
        }
    }


    public final ArrayList<Card> getDeckk() {
        return deckk;
    }
}
