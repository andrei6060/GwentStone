package fileio;

import java.util.ArrayList;

public class Player {
    public boolean turn = false;

    public Deck deck;

    public ArrayList<Card> hand;

    public ArrayList<Card> environmentInHand = new ArrayList<>();


    private int mana = 1;


    public void setTurn(final boolean turn) {
        this.turn = turn;
    }



    public void setMana(final int mana) {
        this.mana = mana;
    }


    public boolean isTurn() {
        return turn;
    }



    public int getMana() {
        return mana;
    }
}
