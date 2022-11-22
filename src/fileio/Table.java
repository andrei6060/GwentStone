package fileio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
@JsonIgnoreProperties({ "hasAttacked", "frozen", "frozencount", "games" })
public final class Table {
    private int turn;
    private int games = 0;

    private int round = 1;
    private boolean roundFirst = false;
    private boolean roundSecond = false;

    public ArrayList<Card> row0 = new ArrayList<>();
    public ArrayList<Card> row1 = new ArrayList<>();
    public ArrayList<Card> row2 = new ArrayList<>();
    public ArrayList<Card> row3 = new ArrayList<>();
    public ArrayList<ArrayList<Card>> table = new ArrayList<ArrayList<Card>>();

    /**
     * Metoda ce (re)initializeaza tabla
     */
    public void table() {
        this.setGames(this.getGames());
        this.table = new ArrayList<ArrayList<Card>>();
        this.table.add(this.row0);
        this.table.add(this.row1);
        this.table.add(this.row2);
        this.table.add(this.row3);
    }

    public  boolean isRoundFirst() {
        return roundFirst;
    }

    public  boolean isRoundSecond() {
        return roundSecond;
    }

    public  ArrayList<Card> getRow0() {
        return row0;
    }

    public  void setRow0(final ArrayList<Card> row0) {
        this.row0 = row0;
    }

    public  ArrayList<Card> getRow1() {
        return row1;
    }

    public  void setRow1(final ArrayList<Card> row1) {
        this.row1 = row1;
    }

    public  ArrayList<Card> getRow2() {
        return row2;
    }

    public  void setRow2(final ArrayList<Card> row2) {
        this.row2 = row2;
    }

    public  ArrayList<Card> getRow3() {
        return row3;
    }

    public  void setRow3(final ArrayList<Card> row3) {
        this.row3 = row3;
    }

    public  ArrayList<ArrayList<Card>> getTable() {
        return table;
    }

     public void setTable(final ArrayList<ArrayList<Card>> table) {
        this.table = table;
    }

     public int getTurn() {
        return turn;
    }

    public  int getRound() {
        return round;
    }

    public  boolean getRoundFirst() {
        return roundFirst;
    }

    public  boolean getRoundSecond() {
        return roundSecond;
    }

    public  void setRoundFirst(final boolean roundFirst) {
        this.roundFirst = roundFirst;
    }

    public  void setRoundSecond(final boolean roundSecond) {
        this.roundSecond = roundSecond;
    }
    public  void setTurn(final int turn) {
        this.turn = turn;
    }

    public  int getGames() {
        return games;
    }

    public  void setGames(final int games) {
        this.games = games;
    }

    public  void setRound(final int round) {
        this.round = round;
    }
}
