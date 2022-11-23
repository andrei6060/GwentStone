package main;

import checker.Checker;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import checker.CheckerConstants;
import fileio.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static java.util.Collections.shuffle;

/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * DO NOT MODIFY MAIN METHOD
     * Call the checker
     *
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(CheckerConstants.TESTS_PATH);
        Path path = Paths.get(CheckerConstants.RESULT_PATH);

        if (Files.exists(path)) {
            File resultFile = new File(String.valueOf(path));
            for (File file : Objects.requireNonNull(resultFile.listFiles())) {
                file.delete();
            }
            resultFile.delete();
        }
        Files.createDirectories(path);

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            String filepath = CheckerConstants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getName(), filepath);
            }
        }

        Checker.calculateScore();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper
                .readValue(new File(CheckerConstants.TESTS_PATH + filePath1), Input.class);

        ArrayNode output = objectMapper.createArrayNode();

        //TODO add here the entry point to your implementation
final int cinci = 5;
final int trei = 3;
final int  zece = 10;
final int sapte = 7;
int wins1 = 0;
int wins2 = 0;
        for (int j = 0; j < inputData.getGames().size(); j++) {
            int okay = 0;
            StartGameInput startGameInput;
            startGameInput = inputData.getGames().get(j).getStartGame();
            DecksInput player1Decks;
            DecksInput player2Decks;
            player1Decks = inputData.getPlayerOneDecks();
            int index1 = inputData.getGames().get(j).getStartGame().getPlayerOneDeckIdx();
            ArrayList<CardInput> player1deck = new ArrayList<>();
            for (int i = 0; i < player1Decks.getDecks().get(index1).size(); i++) {
                CardInput cardd = new CardInput(player1Decks.getDecks().get(index1).get(i));
                player1deck.add(cardd);
            }


            player2Decks = inputData.getPlayerTwoDecks();
            int index2 = inputData.getGames().get(j).getStartGame().getPlayerTwoDeckIdx();
            ArrayList<CardInput> player2deck = new ArrayList<>();

            for (int i = 0; i < player2Decks.getDecks().get(index2).size(); i++) {
                CardInput cardd = new CardInput(player2Decks.getDecks().get(index2).get(i));
                player2deck.add(cardd);
            }
            Card handcard1;


            shuffle(player1deck, new Random(startGameInput.getShuffleSeed()));
            if ((player1deck.get(0).getName().equals("Winterfell"))
                    || (player1deck.get(0).getName()
                    .equals("Firestorm"))
                    || (player1deck.get(0).getName()
                    .equals("Heart Hound"))) {
                handcard1 = new Environment(player1deck.get(0));
            } else {
                handcard1 = new Minion(player1deck.get(0));
            }
            player1deck.remove(0);


            Card handcard2;
            shuffle(player2deck, new Random(inputData.getGames().get(j).getStartGame()
                    .getShuffleSeed()));
            if ((player2deck.get(0).getName().equals("Winterfell"))
                    || (player2deck.get(0).getName().equals("Firestorm"))
                    || (player2deck.get(0).getName()
                    .equals("Heart Hound"))) {
                handcard2 = new Environment(player2deck.get(0));
            } else {
                handcard2 = new Minion(player2deck.get(0));
            }

            player2deck.remove(0);


            Player player1 = new Player();
            Player player2 = new Player();
            Deck deck1 = new Deck(player1deck);
            Deck deck2 = new Deck(player2deck);
            player1.deck = deck1;
            player2.deck = deck2;

            player1.hand = new ArrayList<>();
            player1.hand.add(handcard1);


            player2.hand = new ArrayList<>();
            player2.hand.add(handcard2);



            int turn = inputData.getGames().get(j).getStartGame().getStartingPlayer();
            if (turn == 1) {
                player1.turn = true;
            } else {
                player2.turn = true;
            }
            Hero Hero1 = new Hero(startGameInput.getPlayerOneHero());
            Hero Hero2 = new Hero(startGameInput.getPlayerTwoHero());

            ArrayList<ActionsInput> actionlist = inputData.getGames().get(j).getActions();
            Table table = new Table();
            table.setTurn(turn);

            for (ActionsInput action : actionlist) {
                if ((action.getCommand()
                        .equals("getPlayerDeck")) && (action.getPlayerIdx() == 1)) {
                    output.addObject().put("command", action.getCommand()).
                            put("playerIdx", action.getPlayerIdx()).
                            putPOJO("output", deck1.getDeckk());

                } else if ((action.getCommand()
                        .equals("getPlayerHero")) && (action.getPlayerIdx() == 1)) {
                    output.addObject().put("command", action.getCommand()).
                            put("playerIdx", action.getPlayerIdx()).
                            putPOJO("output", Hero1);

                } else if ((action.getCommand()
                        .equals("getPlayerTurn")) && (action.getPlayerIdx() == 1)) {
                    output.addObject().put("command", action.getCommand()).
                            put("output", startGameInput.getStartingPlayer());

                } else if ((action.getCommand()
                        .equals("getCardsInHand")) && (action.getPlayerIdx() == 1)) {
                    ArrayList<Card> handcopy = new ArrayList<>();
                    for (int i = 0; i < player1.hand.size(); i++) {
                        if ((player1.hand.get(i).getName().equals("Winterfell"))
                                || (player1.hand.get(i).getName().equals("Heart Hound"))
                                || (player1.hand.get(i).getName().equals("Firestorm"))) {
                            Environment card = new Environment((Environment) player1.hand.get(i));
                            handcopy.add(card);
                        } else {
                            Minion card = new Minion((Minion) player1.hand.get(i));
                            handcopy.add(card);
                        }
                    }
                    output.addObject().put("command", action.getCommand()).
                            put("playerIdx", action.getPlayerIdx())
                            .putPOJO("output", handcopy);

                } else if ((action.getCommand()
                        .equals("getPayerMana")) && (action.getPlayerIdx() == 1)) {
                    output.addObject().put("command", action.getCommand()).
                            put("playerIdx", action.getPlayerIdx())
                            .putPOJO("output", player1.getMana());
                } else if ((action.getCommand()
                        .equals("getPlayerDeck")) && (action.getPlayerIdx() == 2)) {
                    output.addObject().put("command", action.getCommand()).
                            put("playerIdx", action.getPlayerIdx()).
                            putPOJO("output", deck2.getDeckk());
                } else if ((action.getCommand()
                        .equals("getPlayerHero")) && (action.getPlayerIdx() == 2)) {
                    output.addObject().put("command", action.getCommand()).
                            put("playerIdx", action.getPlayerIdx()).
                            putPOJO("output", Hero2);
                } else if ((action.getCommand()
                        .equals("getPlayerTurn")) && (action.getPlayerIdx() == 2)) {
                    output.addObject().put("command", action.getCommand()).
                            put("output", startGameInput.getStartingPlayer());

                } else if ((action.getCommand()
                        .equals("getCardsInHand")) && (action.getPlayerIdx() == 2)) {
                    ArrayList<Card> handcopy = new ArrayList<>();
                    for (int i = 0; i < player2.hand.size(); i++) {
                        if ((player2.hand.get(i).getName().equals("Winterfell"))
                                || (player2.hand.get(i).getName().equals("Heart Hound"))
                                || (player2.hand.get(i).getName().equals("Firestorm"))) {
                            Environment card = new Environment((Environment) player2.hand.get(i));
                            handcopy.add(card);
                        } else {
                            Minion card = new Minion((Minion) player2.hand.get(i));
                            handcopy.add(card);
                        }

                    }
                    output.addObject().put("command", action.getCommand()).
                            put("playerIdx", action.getPlayerIdx()).
                            putPOJO("output", handcopy);
                } else if ((action.getCommand()
                        .equals("getPayerMana")) && (action.getPlayerIdx() == 2)) {
                    output.addObject().put("command", action.getCommand()).
                            put("playerIdx", action.getPlayerIdx())
                            .putPOJO("output", player2.getMana());
                } else if (action.getCommand().equals("endPlayerTurn")) {
                    okay = 0;


                    if (table.getTurn() == 1) {
                        if (Hero1.isHasAttacked()) {
                            Hero1.setHasAttacked(false);
                        }
                        for (Card card : table.row2) {
                            if (((Minion) card).isHasAttacked()) {
                                ((Minion) card).setHasAttacked(false);
                            }
                            {
                                if ((card.isFrozen()) && (card.getFrozencount() == 0)) {
                                    card.setFrozencount(1);
                                } else if (card.isFrozen()) {
                                    card.setFrozen(false);
                                    card.setFrozencount(0);
                                }
                            }
                        }
                        for (Card card : table.row3) {
                            if (((Minion) card).isHasAttacked()) {
                                ((Minion) card).setHasAttacked(false);
                            }
                            {
                                if ((card.isFrozen()) && (card.getFrozencount() == 0)) {
                                    card.setFrozencount(1);
                                } else if (card.isFrozen()) {
                                    card.setFrozen(false);
                                    card.setFrozencount(0);
                                }
                            }
                        }


                    }
                    if (table.getTurn() == 2) {
                        if (Hero2.isHasAttacked()) {
                            Hero2.setHasAttacked(false);
                        }
                        for (Card card : table.row0) {
                            if (((Minion) card).isHasAttacked()) {

                                ((Minion) card).setHasAttacked(false);
                            }
                            {
                                if ((card.isFrozen()) && (card.getFrozencount() == 0)) {
                                    card.setFrozencount(1);
                                } else if (card.isFrozen()) {
                                    card.setFrozen(false);
                                    card.setFrozencount(0);
                                }
                            }
                        }
                        for (Card card : table.row1) {
                            if (((Minion) card).isHasAttacked()) {
                                ((Minion) card).setHasAttacked(false);
                            }

                            {
                                if ((card.isFrozen()) && (card.getFrozencount() == 0)) {
                                    card.setFrozencount(1);
                                } else if (card.isFrozen()) {
                                    card.setFrozen(false);
                                    card.setFrozencount(0);
                                }
                            }
                        }

                    }
                    if (table.getTurn() == 1) {
                        player1.turn = false;
                        player2.turn = true;
                        table.setTurn(2);
                        table.setRoundFirst(true);
                    } else {
                        player2.turn = false;
                        player1.turn = true;
                        table.setTurn(1);
                        table.setRoundSecond(true);
                    }

                    if (table.getRoundFirst() && table.getRoundSecond()) {
                        for (Card card : table.row0) {
                            if ((card.isFrozen()) && (card.getFrozencount() == 0)) {
                                card.setFrozencount(1);
                            } else if (card.isFrozen()) {
                                card.setFrozen(false);
                                card.setFrozencount(0);
                            }
                        }
                        for (Card card : table.row1) {
                            if ((card.isFrozen()) && (card.getFrozencount() == 0)) {
                                card.setFrozencount(1);
                            } else if (card.isFrozen()) {
                                card.setFrozen(false);
                                card.setFrozencount(0);
                            }
                        }
                        for (Card card : table.row2) {
                            if ((card.isFrozen()) && (card.getFrozencount() == 0)) {
                                card.setFrozencount(1);
                            } else if (card.isFrozen()) {
                                card.setFrozen(false);
                                card.setFrozencount(0);
                            }
                        }
                        for (Card card : table.row3) {
                            if ((card.isFrozen()) && (card.getFrozencount() == 0)) {
                                card.setFrozencount(1);
                            } else if (card.isFrozen()) {
                                card.setFrozen(false);
                                card.setFrozencount(0);
                            }
                        }
                        table.table();
                        if (player1.deck.deckk.size() > 0) {
                            okay = 1;
                            player1.hand.add(player1.deck.deckk.get(0));
                            player2.hand.add(player2.deck.deckk.get(0));
                            player1.deck.deckk.remove(0);
                            player2.deck.deckk.remove(0);
                            table.setRoundFirst(false);
                            table.setRoundSecond(false);
                            table.setRound(table.getRound() + 1);
                            if (table.getRound() <= zece) {
                                player1.setMana(player1.getMana() + table.getRound());
                                player2.setMana(player2.getMana() + table.getRound());
                            } else {
                                player1.setMana(player1.getMana() + zece);
                                player2.setMana(player2.getMana() + zece);
                            }
                        }
                        if ((okay == 0) && (table.getRound() == sapte)) {
                            table.setRound(table.getRound() + 1);
                            player1.setMana(player1.getMana() + table.getRound());
                            player2.setMana(player2.getMana() + table.getRound());
                        }
                    }

                } else if (action.getCommand().equals("placeCard")) {
                    int handIdx = action.getHandIdx();
                    int ok = 0;
                    if (table.getTurn() == 1) {


                        if (handIdx < player1.hand.size()) {
                            if ((player1.hand.get(handIdx).getName().equals("Winterfell"))
                                    || (player1.hand.get(handIdx).getName().equals("Heart Hound"))
                                    || (player1.hand.get(handIdx).getName().equals("Firestorm"))) {
                                output.addObject().put("command", action.getCommand()).
                                        put("error", "Cannot place environment card on table.")
                                        .put("handIdx", action.getHandIdx());
                                ok = 1;
                            } else if ((table.row3.size() < cinci) && ((player1.hand.get(handIdx)
                                    .getName()
                                    .equals("Sentinel"))
                                    || (player1.hand.get(handIdx).getName().equals("Berserker"))
                                    || (player1.hand.get(handIdx).getName().equals("Disciple"))
                                    || (player1.hand.get(handIdx).getName()
                                    .equals("The Cursed One")))) {
                              if (player1.getMana() - player1.hand.get(handIdx).getMana() >= 0) {
                                    table.row3.add(player1.hand.get(handIdx));
                                } else {
                                    ok = 1;
                                    output.addObject().put("command", action.getCommand()).
                                            put("error", "Not enough mana to place card on table.")
                                            .put("handIdx", action.getHandIdx());
                                }
                            } else if ((table.row3.size() == cinci) && ((player1.hand.get(handIdx)
                                    .getName().equals("Sentinel"))
                                    || (player1.hand.get(handIdx).getName().equals("Berserker"))
                                    || (player1.hand.get(handIdx).getName().equals("Disciple"))
                                    || (player1.hand.get(handIdx).getName()
                                    .equals("The Cursed One")))) {
                                output.addObject().put("command", action.getCommand()).
                                        put("handIdx", action.getHandIdx())
                                        .put("error",
                                                "Cannot place card on table since row is full.");
                                ok = 1;

                            } else if ((table.row2.size() < cinci) && ((player1.hand.get(handIdx)
                                  .getName().equals("Miraj"))
                                    || (player1.hand.get(handIdx).getName().equals("The Ripper"))
                                    || (player1.hand.get(handIdx).getName().equals("Goliath"))
                                    || (player1.hand.get(handIdx).getName().equals("Warden")))) {
                              if (player1.getMana() - player1.hand.get(handIdx).getMana() >= 0) {
                                    table.row2.add(player1.hand.get(handIdx));
                                } else {
                                    ok = 1;
                                    output.addObject().put("command", action.getCommand()).
                                            put("error",
                                                    "Not enough mana to place card on table.").
                                            put("handIdx", action.getHandIdx());
                                }
                            } else if ((table.row2.size() == cinci) && ((player1.hand.get(handIdx)
                                    .getName().equals("Miraj"))
                                    || (player1.hand.get(handIdx).getName().equals("The Ripper"))
                                    || (player1.hand.get(handIdx).getName().equals("Goliath"))
                                    || (player1.hand.get(handIdx).getName().equals("Warden")))) {
                                output.addObject().put("command", action.getCommand()).
                                        put("handIdx", action.getHandIdx()).put("error",
                                                "Cannot place card on table since row is full.");
                                ok = 1;
                            }

                            if (ok == 0) {
                                player1.setMana(player1.getMana() - player1.hand
                                        .get(handIdx).getMana());
                                player1.hand.remove(handIdx);
                            }

                        }
                    } else {


                        if (handIdx < player2.hand.size()) {
                            if ((player2.hand.get(handIdx).getName().equals("Winterfell"))
                                    || (player2.hand.get(handIdx).getName().equals("Heart Hound"))
                                  || (player2.hand.get(handIdx).getName().equals("Firestorm"))) {
                                output.addObject().put("command", action.getCommand()).
                                        put("error", "Cannot place environment card on table.").
                                        put("handIdx", action.getHandIdx());
                                ok = 1;
                            } else if ((table.row0.size() < cinci) && ((player2.hand.get(handIdx)
                                    .getName().equals("Sentinel"))
                                    || (player2.hand.get(handIdx).getName().equals("Berserker"))
                                    || (player2.hand.get(handIdx).getName().equals("Disciple"))
                                    || (player2.hand.get(handIdx).getName()
                                    .equals("The Cursed One")))) {
                              if (player2.getMana() - player2.hand.get(handIdx).getMana() >= 0) {
                                    table.row0.add(player2.hand.get(handIdx));
                                } else {
                                    ok = 1;
                                    output.addObject().put("command", action.getCommand()).
                                            put("error",
                                                    "Not enough mana to place card on table.").
                                            put("handIdx", action.getHandIdx());
                                }
                            } else if ((table.row0.size() == cinci) && ((player2.hand.get(handIdx)
                                    .getName().equals("Sentinel"))
                                    || (player2.hand.get(handIdx).getName().equals("Berserker"))
                                    || (player2.hand.get(handIdx).getName().equals("Disciple"))
                                    || (player2.hand.get(handIdx).getName()
                                    .equals("The Cursed One")))) {
                                output.addObject().put("command", action.getCommand()).
                                        put("handIdx", action.getHandIdx()).put("error",
                                                "Cannot place card on table since row is full.");
                                ok = 1;
                            } else if ((table.row1.size() < cinci) && ((player2.hand.get(handIdx)
                                    .getName().equals("Miraj"))
                                    || (player2.hand.get(handIdx).getName().equals("The Ripper"))
                                    || (player2.hand.get(handIdx).getName().equals("Goliath"))
                                    || (player2.hand.get(handIdx).getName().equals("Warden")))) {
                              if (player2.getMana() - player2.hand.get(handIdx).getMana() >= 0) {
                                    table.row1.add(player2.hand.get(handIdx));
                                } else {
                                    ok = 1;
                                    output.addObject().put("command", action.getCommand()).
                                            put("error",
                                                    "Not enough mana to place card on table.").
                                            put("handIdx", action.getHandIdx());
                                }
                            } else if ((table.row1.size() == cinci) && ((player2.hand.get(handIdx)
                                    .getName().equals("Miraj"))
                                    || (player2.hand.get(handIdx).getName().equals("The Ripper"))
                                    || (player2.hand.get(handIdx).getName().equals("Goliath"))
                                    || (player2.hand.get(handIdx).getName().equals("Warden")))) {
                                output.addObject().put("command", action.getCommand()).
                                        put("handIdx", action.getHandIdx()).put("error",
                                                "Cannot place card on table since row is full.");
                                ok = 1;
                            }
                            if (ok == 0) {
                                player2.setMana(player2.getMana() - player2.hand
                                        .get(handIdx).getMana());
                                player2.hand.remove(handIdx);
                            }

                        }
                    }
                    table.table();

                } else if (action.getCommand().equals("getCardsOnTable")) {
                    ArrayList<ArrayList<Card>> table2 = new ArrayList<>();
                    ArrayList<Card> row = new ArrayList<>(table.getRow0());
                    table2.add(row);
                    ArrayList<Card> row2 = new ArrayList<>(table.getRow1());
                    table2.add(row2);
                    ArrayList<Card> row3 = new ArrayList<>(table.getRow2());
                    table2.add(row3);
                    ArrayList<Card> row4 = new ArrayList<>(table.getRow3());
                    table2.add(row4);
                    output.addObject().put("command", action.getCommand())
                            .putPOJO("output", table2);


                } else if (action.getCommand().equals("getPlayerTurn")) {
                    output.addObject().put("command", action.getCommand()).
                            put("output", table.getTurn());
                } else if (action.getCommand().equals("getPlayerMana")) {
                    if (action.getPlayerIdx() == 1) {
                        int mana = player1.getMana();
                        output.addObject().put("command", action.getCommand())
                                .put("playerIdx", action.getPlayerIdx()).put("output", mana);
                    } else {
                        int mana = player2.getMana();
                        output.addObject().put("command", action.getCommand())
                                .put("playerIdx", action.getPlayerIdx()).put("output", mana);

                    }
                } else if (action.getCommand().equals("getEnvironmentCardsInHand")) {
                    ArrayList<Environment> envinhand = new ArrayList<>();
                    if (action.getPlayerIdx() == 1) {
                        for (Card card : player1.hand) {
                            if ((card.getName().equals("Winterfell"))
                                    || (card.getName().equals("Heart Hound"))
                                    || (card.getName().equals("Firestorm"))) {
                                Environment env = new Environment((Environment) card);
                                envinhand.add(env);
                            }
                        }
                        output.addObject().put("command", action.getCommand()).
                                put("playerIdx", action.getPlayerIdx()).
                                putPOJO("output", envinhand);
                    } else {
                        for (Card card : player2.hand) {
                            if ((card.getName().equals("Winterfell"))
                                    || (card.getName().equals("Heart Hound"))
                                    || (card.getName().equals("Firestorm"))) {
                                Environment env = new Environment((Environment) card);
                                envinhand.add(env);
                            }
                        }
                        output.addObject().put("command", action.getCommand()).
                                put("playerIdx", action.getPlayerIdx()).
                                putPOJO("output", envinhand);

                    }
                } else if (action.getCommand().equals("useEnvironmentCard")) {


                    ArrayList<Card> newrow = new ArrayList<>();
                    if ((table.getTurn() == 1) && (action.getHandIdx() < player1.hand.size())) {

                        if ((player1.hand.get(action.getHandIdx()).getName()
                                .equals("Sentinel"))
                                || (player1.hand.get(action.getHandIdx()).getName()
                                .equals("Berserker"))
                                || (player1.hand.get(action.getHandIdx()).getName()
                                .equals("Goliath"))
                                || (player1.hand.get(action.getHandIdx()).getName()
                                .equals("Warden"))
                                || (player1.hand.get(action.getHandIdx()).getName()
                                .equals("Miraj"))
                                || (player1.hand.get(action.getHandIdx()).getName()
                                .equals("The Ripper"))
                                || (player1.hand.get(action.getHandIdx()).getName()
                                .equals("Disciple"))
                                || (player1.hand.get(action.getHandIdx()).getName()
                                .equals("The Cursed One"))) {
                            output.addObject().put("command", action.getCommand()).
                                    put("handIdx", action.getHandIdx()).
                                    put("affectedRow", action.getAffectedRow()).
                                    put("error", "Chosen card is not of type environment.");
                        } else if (player1.getMana() < player1.hand.get(action.getHandIdx())
                                .getMana()) {
                            output.addObject().put("command", action.getCommand()).
                                    put("handIdx", action.getHandIdx()).
                                    put("affectedRow", action.getAffectedRow()).
                                    put("error", "Not enough mana to use environment card.");

                        } else if ((action.getAffectedRow() == 2) || (action
                                .getAffectedRow() == trei)) {
                            output.addObject().put("command", action.getCommand()).
                                    put("handIdx", action.getHandIdx()).
                                    put("affectedRow", action.getAffectedRow()).
                                    put("error", "Chosen row does not belong to the enemy.");
                        } else {
                            if (player1.hand.get(action.getHandIdx()).getName()
                                    .equals("Firestorm")) {
                                Firestorm env = new Firestorm((Environment) player1
                                        .hand.get(action.getHandIdx()));
                                env.ability(table, action.getAffectedRow());
                                player1.setMana(player1.getMana() - player1.hand
                                        .get(action.getHandIdx()).getMana());
                                player1.hand.remove(action.getHandIdx());

                            } else if (player1.hand.get(action.getHandIdx())
                                    .getName().equals("Winterfell")) {
                                Winterfell env = new Winterfell((Environment) player1
                                        .hand.get(action.getHandIdx()));
                                env.ability(table, action.getAffectedRow());
                                player1.setMana(player1.getMana() - player1
                                        .hand.get(action.getHandIdx()).getMana());
                                player1.hand.remove(action.getHandIdx());

                            } else if (player1.hand.get(action.getHandIdx())
                                    .getName().equals("Heart Hound")) {
                                if (table.table.get(trei - action.getAffectedRow())
                                        .size() >= cinci) {
                                    output.addObject().put("command", action.getCommand()).
                                            put("handIdx", action.getHandIdx()).
                                            put("affectedRow", action.getAffectedRow()).
                                            put("error",
                                     "Cannot steal enemy card since the player's row is full.");
                                } else {
                                    HeartHound env = new HeartHound((Environment) player1
                                            .hand.get(action.getHandIdx()));
                                    env.ability(table, action.getAffectedRow());
                                    player1.setMana(player1.getMana() - player1.hand
                                            .get(action.getHandIdx()).getMana());
                                    player1.hand.remove(action.getHandIdx());
                                }
                            }

                        }

                    } else if ((table.getTurn() == 2) && (action.getHandIdx() < player2
                            .hand.size())) {
                        if ((player2.hand.get(action.getHandIdx()).getName()
                                .equals("Sentinel"))
                                || (player2.hand.get(action.getHandIdx()).getName()
                                .equals("Berserker"))
                                || (player2.hand.get(action.getHandIdx()).getName()
                                .equals("Goliath"))
                                || (player2.hand.get(action.getHandIdx()).getName()
                                .equals("Warden"))
                                || (player2.hand.get(action.getHandIdx()).getName()
                                .equals("Miraj"))
                                || (player2.hand.get(action.getHandIdx()).getName()
                                .equals("The Ripper"))
                                || (player2.hand.get(action.getHandIdx()).getName()
                                .equals("Disciple"))
                                || (player2.hand.get(action.getHandIdx()).getName()
                                .equals("The Cursed One"))) {
                            output.addObject().put("command", action.getCommand()).
                                    put("handIdx", action.getHandIdx()).
                                    put("affectedRow", action.getAffectedRow()).
                                    put("error",
                                            "Chosen card is not of type environment.");
                        } else if (player2.getMana() < player2.hand.get(action.getHandIdx())
                                .getMana()) {
                            output.addObject().put("command", action.getCommand()).
                                    put("handIdx", action.getHandIdx()).
                                    put("affectedRow", action.getAffectedRow()).
                                    put("error",
                                            "Not enough mana to use environment card.");

                        } else if ((action.getAffectedRow() == 0)
                                || (action.getAffectedRow() == 1)) {
                            output.addObject().put("command", action.getCommand()).
                                    put("handIdx", action.getHandIdx()).
                                    put("affectedRow", action.getAffectedRow()).
                                    put("error",
                                            "Chosen row does not belong to the enemy.");
                        } else {

                            if (player2.hand.get(action.getHandIdx()).getName()
                                    .equals("Firestorm")) {
                                Firestorm env = new Firestorm((Environment) player2
                                        .hand.get(action.getHandIdx()));
                                env.ability(table, action.getAffectedRow());
                                player2.setMana(player2.getMana() - player2
                                        .hand.get(action.getHandIdx()).getMana());
                                player2.hand.remove(action.getHandIdx());

                            } else if (player2.hand.get(action.getHandIdx()).getName()
                                    .equals("Winterfell")) {
                                Winterfell env = new Winterfell((Environment) player2
                                        .hand.get(action.getHandIdx()));
                                env.ability(table, action.getAffectedRow());
                                player2.setMana(player2.getMana() - player2
                                        .hand.get(action.getHandIdx()).getMana());
                                player2.hand.remove(action.getHandIdx());
                            } else if (player2.hand.get(action.getHandIdx()).getName()
                                    .equals("Heart Hound")) {
                                if (table.table.get(trei - action.getAffectedRow())
                                        .size() >= cinci) {
                                    output.addObject().put("command", action.getCommand()).
                                            put("handIdx", action.getHandIdx()).
                                            put("affectedRow", action.getAffectedRow()).
                                            put("error",
                                     "Cannot steal enemy card since the player's row is full.");
                                } else {
                                    HeartHound env = new HeartHound((Environment) player2.hand
                                            .get(action.getHandIdx()));
                                    env.ability(table, action.getAffectedRow());
                                    player2.setMana(player2.getMana() - player2.hand
                                            .get(action.getHandIdx()).getMana());
                                    player2.hand.remove(action.getHandIdx());
                                }
                            }

                        }
                    }
                } else if (action.getCommand().equals("getCardAtPosition")) {
                    int x = action.getX();
                    int y = action.getY();
                    if (x == 0) {

                        if ((x < table.table.size()) && (y < table.table.get(x).size())) {
                            Minion card = new Minion((Minion) table.row0.get(y));
                            output.addObject().put("command", action.getCommand())
                                    .put("x", action.getX())
                                    .put("y", action.getY()).putPOJO("output", card);
                        } else {
                            output.addObject().put("command", action.getCommand())
                                    .put("x", action.getX())
                                    .put("y", action.getY())
                                    .putPOJO("output", "No card available at that position.");
                        }
                    } else if (x == 1) {
                        if ((x < table.table.size()) && (y < table.table.get(x).size())) {
                            Minion card = new Minion((Minion) table.row1.get(y));

                            output.addObject().put("command", action.getCommand())
                                    .put("x", action.getX())
                                    .put("y", action.getY())
                                    .putPOJO("output", card);
                        } else {
                            output.addObject().put("command", action
                                    .getCommand()).put("x", action.getX())
                                    .put("y", action.getY())
                                    .putPOJO("output",
                                            "No card available at that position.");
                        }
                    } else if (x == 2) {

                        if ((x < table.table.size()) && (y < table.table.get(x).size())) {
                            Minion card = new Minion((Minion) table.row2.get(y));
                            output.addObject().put("command", action.getCommand())
                                    .put("x", action.getX()).put("y", action
                                            .getY()).putPOJO("output", card);
                        } else {
                            output.addObject().put("command", action.getCommand())
                                    .put("x", action.getX()).put("y", action.getY())
                                    .putPOJO("output", "No card available at that position.");
                        }
                    } else if (x == trei) {

                        if (x < table.table.size() && (y < table.table.get(x).size())) {
                            Minion card = new Minion((Minion) table.row3.get(y));
                            output.addObject().put("command", action.getCommand())
                                    .put("x", action.getX()).put("y", action.getY())
                                    .putPOJO("output", card);
                        } else {
                            output.addObject().put("command", action.getCommand())
                                    .put("x", action.getX()).put("y", action.getY())
                                    .putPOJO("output", "No card available at that position.");
                        }
                    }
                } else if (action.getCommand().equals("getFrozenCardsOnTable")) {
                    ArrayList<Card> frozen = new ArrayList<>();
                    for (Card card : table.row0) {
                        if (card.isFrozen()) {
                            frozen.add(card);
                        }
                    }
                    for (Card card : table.row1) {
                        if (card.isFrozen()) {
                            frozen.add(card);
                        }
                    }
                    for (Card card : table.row2) {
                        if (card.isFrozen()) {
                            frozen.add(card);
                        }
                    }
                    for (Card card : table.row3) {
                        if (card.isFrozen()) {
                            frozen.add(card);
                        }
                    }
                    output.addObject().put("command", action.getCommand())
                            .putPOJO("output", frozen);
                } else if (action.getCommand().equals("cardUsesAttack")) {
                    int x = action.getCardAttacker().getX();
                    int y = action.getCardAttacker().getY();
                    int xattacked = action.getCardAttacked().getX();
                    int yattacked = action.getCardAttacked().getY();

                    Minion minion = new Minion((Minion) table.table.get(x).get(y));
                    int ok = 0;
                    if ((table.getTurn() == 1) && ((xattacked == 2) || (xattacked == trei))) {

                        output.addObject().put("command", action.getCommand())
                                .putPOJO("cardAttacker", action.getCardAttacker())
                                .putPOJO("cardAttacked", action.getCardAttacked())
                                .put("error", "Attacked card does not belong to the enemy.");
                        ok = 1;
                    } else if ((table.getTurn() == 2) && ((xattacked == 0) || (xattacked == 1))) {

                        output.addObject().put("command", action.getCommand())
                                .putPOJO("cardAttacker", action.getCardAttacker())
                                .putPOJO("cardAttacked", action.getCardAttacked())
                                .put("error", "Attacked card does not belong to the enemy.");
                        ok = 1;
                    } else if (((Minion) table.table.get(x).get(y)).isHasAttacked()) {

                        output.addObject().put("command", action.getCommand())
                                .putPOJO("cardAttacker", action.getCardAttacker())
                                .putPOJO("cardAttacked", action.getCardAttacked())
                                .put("error", "Attacker card has already attacked this turn.");
                        ok = 1;
                    } else if (table.table.get(x).get(y).isFrozen()) {

                        output.addObject().put("command", action.getCommand())
                                .putPOJO("cardAttacker", action.getCardAttacker())
                                .putPOJO("cardAttacked", action.getCardAttacked())
                                .put("error", "Attacker card is frozen.");
                        ok = 1;
                    } else {

                        int tank = 0;

                        if (table.getTurn() == 1) {


                            for (Card card : table.table.get(1)) {
                                if ((card.getName().equals("Goliath")) || card.getName()
                                        .equals("Warden")) {
                                    tank = 1;
                                }
                            }
                            if (tank == 0) {
                                ok = 1;
                            } else {
                                if ((table.table.get(xattacked).get(yattacked).getName()
                                        .equals("Goliath"))
                                        || ((table.table.get(xattacked).get(yattacked)
                                        .getName().equals("Warden")))) {
                                    ok = 1;
                                } else {
                                    output.addObject().put("command", action.getCommand())
                                            .putPOJO("cardAttacker", action.getCardAttacker())
                                            .putPOJO("cardAttacked", action.getCardAttacked())
                                            .put("error", "Attacked card is not of type 'Tank'.");
                                }
                            }

                        } else if (table.getTurn() == 2) {
                            for (Card card : table.table.get(2)) {
                                if ((card.getName().equals("Goliath")) || card.getName()
                                        .equals("Warden")) {
                                    tank = 1;
                                }
                            }

                            if (tank == 0) {
                                ok = 1;
                            } else {
                                if ((table.table.get(xattacked).get(yattacked).getName()
                                        .equals("Goliath"))
                                        || ((table.table.get(xattacked).get(yattacked).getName()
                                        .equals("Warden")))) {
                                    ok = 1;
                                } else {
                                    output.addObject().put("command", action.getCommand())
                                            .putPOJO("cardAttacker", action.getCardAttacker())
                                            .putPOJO("cardAttacked", action.getCardAttacked())
                                            .put("error", "Attacked card is not of type 'Tank'.");
                                }
                            }

                        }
                        if (ok == 1) {

                            int oldhealt = ((Minion) table.table.get(xattacked).get(yattacked))
                                    .getHealth();
                            int damage = minion.getAttackDamage();
                            ((Minion) table.table.get(xattacked).get(yattacked))
                                    .setHealth(oldhealt - damage);

                            if (((Minion) table.table.get(xattacked).get(yattacked))
                                    .getHealth() <= 0) {
                                table.table.get(xattacked).remove(yattacked);
                            }

                            ((Minion) table.table.get(x).get(y)).setHasAttacked(true);

                            if (x == 0) {
                                table.row0 = table.table.get(x);
                            } else if (x == 1) {
                                table.row1 = table.table.get(x);
                            } else if (x == 2) {
                                table.row2 = table.table.get(x);
                            } else if (x == trei) {
                                table.row3 = table.table.get(x);
                            }
                            table.table();
                            if (xattacked == 0) {
                                table.row0 = table.table.get(xattacked);
                            } else if (xattacked == 1) {
                                table.row1 = table.table.get(xattacked);
                            } else if (xattacked == 2) {
                                table.row2 = table.table.get(xattacked);
                            } else if (xattacked == trei) {
                                table.row3 = table.table.get(xattacked);
                            }

                            table.table();
                        }

                    }

                } else if (action.getCommand().equals("cardUsesAbility")) {
                    int ok = 0;
                    int tank = 0;
                    int x = action.getCardAttacker().getX();
                    int y = action.getCardAttacker().getY();

                    int xattacked = action.getCardAttacked().getX();
                    int yattacked = action.getCardAttacked().getY();
                    if (table.table.get(x).get(y).isFrozen()) {
                        output.addObject().put("command", action.getCommand())
                                .putPOJO("cardAttacker", action.getCardAttacker())
                                .putPOJO("cardAttacked", action.getCardAttacked())
                                .put("error", "Attacker card is frozen.");
                    } else if (((Minion) table.table.get(x).get(y)).isHasAttacked()) {
                        output.addObject().put("command", action.getCommand())
                                .putPOJO("cardAttacker", action.getCardAttacker())
                                .putPOJO("cardAttacked", action.getCardAttacked())
                                .put("error", "Attacker card has already attacked this turn.");
                    } else if (table.table.get(x).get(y).getName().equals("Disciple")) {
                        if ((table.getTurn() == 1) && ((xattacked == 0) || (xattacked == 1))) {
                            output.addObject().put("command", action.getCommand())
                                    .putPOJO("cardAttacker", action.getCardAttacker())
                                    .putPOJO("cardAttacked", action.getCardAttacked())
                                    .put("error",
                                            "Attacked card does not belong to the current player.");
                        } else if ((table.getTurn() == 2) && ((xattacked == trei)
                                || (xattacked == 2))) {
                            output.addObject().put("command", action.getCommand())
                                    .putPOJO("cardAttacker", action.getCardAttacker())
                                    .putPOJO("cardAttacked", action.getCardAttacked())
                                    .put("error",
                                            "Attacked card does not belong to the current player.");
                        } else {
                            ((Minion) table.table.get(x).get(y)).setHasAttacked(true);
                            table.table();
                            Disciple disciple = new Disciple((Minion) table.table.get(x).get(y));
                            disciple.godsplan(table, xattacked, yattacked);
                        }
                    } else if ((table.table.get(x).get(y).getName().equals("Miraj"))
                            || (table.table.get(x).get(y).getName().equals("The Cursed One"))
                            || (table.table.get(x).get(y).getName().equals("The Ripper"))) {
                        if ((table.getTurn() == 1) && ((xattacked == 2) || (xattacked == trei))) {
                            output.addObject().put("command", action.getCommand())
                                    .putPOJO("cardAttacker", action.getCardAttacker())
                                    .putPOJO("cardAttacked", action.getCardAttacked())
                                    .put("error", "Attacked card does not belong to the enemy.");

                        } else if ((table.getTurn() == 1) && ((xattacked == 1)
                                || (xattacked == 0))) {

                            for (Card card : table.table.get(1)) {
                                if ((card.getName().equals("Goliath")) || card.getName()
                                        .equals("Warden")) {
                                    tank = 1;
                                }
                            }
                            if (tank == 0) {
                                ok = 1;
                            } else {
                                if ((table.table.get(xattacked).get(yattacked).getName()
                                        .equals("Goliath")) || ((table.table.get(xattacked)
                                        .get(yattacked).getName().equals("Warden")))) {
                                    ok = 1;
                                } else {
                                    output.addObject().put("command", action.getCommand())
                                            .putPOJO("cardAttacker", action.getCardAttacker())
                                            .putPOJO("cardAttacked", action.getCardAttacked())
                                            .put("error", "Attacked card is not of type 'Tank'.");
                                }
                            }


                        } else if ((table.getTurn() == 2) && ((xattacked == 1)
                                || (xattacked == 0))) {
                            output.addObject().put("error",
                                    "Attacked card does not belong to the current player.");
                        } else if ((table.getTurn() == 2) && ((xattacked == trei)
                                || (xattacked == 2))) {
                            for (Card card : table.table.get(2)) {
                                if ((card.getName().equals("Goliath")) || card.getName()
                                        .equals("Warden")) {
                                    tank = 1;
                                }
                            }

                            if (tank == 0) {
                                ok = 1;
                            } else {
                                if ((table.table.get(xattacked).get(yattacked).getName()
                                        .equals("Goliath"))
                                        || ((table.table.get(xattacked).get(yattacked)
                                        .getName().equals("Warden")))) {
                                    ok = 1;
                                } else {
                                    output.addObject().put("command", action.getCommand())
                                            .putPOJO("cardAttacker", action.getCardAttacker())
                                            .putPOJO("cardAttacked", action.getCardAttacked())
                                            .put("error", "Attacked card is not of type 'Tank'.");
                                }
                            }
                        }
                        if (ok == 1) {
                            if (table.table.get(x).get(y).getName().equals("Miraj")) {
                                ((Minion) table.table.get(x).get(y)).setHasAttacked(true);
                                table.table();
                                Miraj miraj = new Miraj((Minion) (table.table.get(x).get(y)));
                                miraj.skyjack(table, x, y, xattacked, yattacked);

                            } else if (table.table.get(x).get(y).getName().equals("The Ripper")) {
                                ((Minion) table.table.get(x).get(y)).setHasAttacked(true);
                                table.table();
                                TheRipper rip = new TheRipper((Minion) (table.table.get(x).get(y)));
                                rip.weakknees(table, xattacked, yattacked);

                            } else if (table.table.get(x).get(y).getName()
                                    .equals("The Cursed One")) {
                                ((Minion) table.table.get(x).get(y)).setHasAttacked(true);
                                table.table();
                                TheCursedOne cursed = new TheCursedOne((Minion) (table
                                        .table.get(x).get(y)));
                                cursed.shapeshift(table, xattacked, yattacked);

                            }
                        }
                    }

                } else if (action.getCommand().equals("useAttackHero")) {
                    int tank = 0;
                    int x = action.getCardAttacker().getX();
                    int y = action.getCardAttacker().getY();
                    if (table.table.get(x).get(y).isFrozen()) {
                        output.addObject().put("command", action.getCommand())
                                .putPOJO("cardAttacker", action.getCardAttacker())
                                .put("error", "Attacker card is frozen.");
                    } else if (((Minion) table.table.get(x).get(y)).isHasAttacked()) {
                        output.addObject().put("command", action.getCommand())
                                .putPOJO("cardAttacker", action.getCardAttacker())
                                .put("error", "Attacker card has already attacked this turn.");
                    } else if (table.getTurn() == 1) {

                        for (Card card : table.table.get(1)) {
                            if ((card.getName().equals("Goliath")) || card.getName()
                                    .equals("Warden")) {
                                tank = 1;
                            }
                        }
                        if (tank == 0) {
                            Hero Hero = new Hero(Hero2);
                            Hero.setHealth(Hero.getHealth() - ((Minion) table
                                    .table.get(x).get(y)).getAttackDamage());
                            ((Minion) table.table.get(x).get(y)).setHasAttacked(true);
                            table.table();
                            if (Hero.getHealth() <= 0) {
                                output.addObject().put("gameEnded",
                                        "Player one killed the enemy hero.");
                                wins1 = wins1 + 1;
                            }
                            Hero2 = new Hero(Hero);
                        } else {

                            output.addObject().put("command", action.getCommand())
                                    .putPOJO("cardAttacker", action.getCardAttacker())
                                    .put("error", "Attacked card is not of type 'Tank'.");
                        }


                    } else if (table.getTurn() == 2) {
                        for (Card card : table.table.get(2)) {
                            if ((card.getName().equals("Goliath"))
                                    || card.getName().equals("Warden")) {
                                tank = 1;
                            }
                        }

                        if (tank == 0) {
                            Hero Hero = new Hero(Hero1);
                            Hero.setHealth(Hero.getHealth() - ((Minion) table
                                    .table.get(x).get(y)).getAttackDamage());
                            ((Minion) table.table.get(x).get(y)).setHasAttacked(true);
                            table.table();
                            if (Hero.getHealth() <= 0) {
                                output.addObject().put("gameEnded",
                                        "Player two killed the enemy hero.");
                                wins2 = wins2 + 1;
                            }
                            Hero1 = new Hero(Hero);
                        } else {

                            output.addObject().put("command", action.getCommand())
                                    .putPOJO("cardAttacker", action.getCardAttacker())
                                    .put("error", "Attacked card is not of type 'Tank'.");
                        }


                    }
                } else if (action.getCommand().equals("useHeroAbility")) {
                    int ok = 1;
                    if (table.getTurn() == 1) {

                        if (player1.getMana() - Hero1.getMana() < 0) {
                            output.addObject().put("command", action.getCommand())
                                    .put("affectedRow", action.getAffectedRow())
                                    .put("error", "Not enough mana to use hero's ability.");
                            ok = 0;
                        } else if (Hero1.isHasAttacked()) {
                            output.addObject().put("command", action.getCommand())
                                    .put("affectedRow", action.getAffectedRow())
                                    .put("error", "Hero has already attacked this turn.");
                            ok = 0;
                        } else if ((Hero1.getName().equals("Lord Royce"))
                                || (Hero1.getName().equals("Empress Thorina"))) {
                            if ((action.getAffectedRow() == 2)
                                    || (action.getAffectedRow() == trei)) {
                                output.addObject().put("command", action.getCommand())
                                        .put("affectedRow", action.getAffectedRow())
                                      .put("error", "Selected row does not belong to the enemy.");
                                ok = 0;
                            }
                            if (ok == 1) {
                                if (Hero1.getName().equals("Lord Royce")) {
                                    int mana = player1.getMana() - Hero1.getMana();
                                    player1.setMana(mana);
                                    LordRoyce lord = new LordRoyce(Hero1);
                                    lord.subzero(table, action.getAffectedRow());
                                    lord.setHasAttacked(true);
                                    Hero1 = new Hero(lord);
                                    table.table();
                                } else if (Hero1.getName().equals("Empress Thorina")) {
                                    int mana = player1.getMana() - Hero1.getMana();
                                    player1.setMana(mana);
                                    EmpressThorina thorina = new EmpressThorina(Hero1);
                                    thorina.lowblow(table, action.getAffectedRow());
                                    thorina.setHasAttacked(true);
                                    Hero1 = new Hero(thorina);
                                    table.table();

                                }
                            }


                        } else if ((Hero1.getName().equals("General Kocioraw"))
                                || (Hero1.getName().equals("King Mudface"))) {
                            if ((action.getAffectedRow() == 0) || (action.getAffectedRow() == 1)) {
                                output.addObject().put("command", action.getCommand())
                                        .put("affectedRow", action.getAffectedRow())
                                        .put("error",
                                           "Selected row does not belong to the current player.");
                                ok = 0;
                            }

                            if (ok == 1) {
                                if (Hero1.getName().equals("General Kocioraw")) {
                                    int mana = player1.getMana() - Hero1.getMana();
                                    player1.setMana(mana);
                                    GeneralKocioraw general = new GeneralKocioraw(Hero1);
                                    general.bloodthirst(table, action.getAffectedRow());
                                    general.setHasAttacked(true);
                                    Hero1 = new Hero(general);
                                    table.table();

                                } else if (Hero1.getName().equals("King Mudface")) {
                                    int mana = player1.getMana() - Hero1.getMana();
                                    player1.setMana(mana);
                                    KingMudface king = new KingMudface(Hero1);
                                    king.earthborn(table, action.getAffectedRow());
                                    king.setHasAttacked(true);
                                    Hero1 = new Hero(king);
                                    table.table();
                                }
                            }


                        }
                    } else if (table.getTurn() == 2) {

                        if (player2.getMana() - Hero2.getMana() < 0) {
                            output.addObject().put("command", action.getCommand())
                                    .put("affectedRow", action.getAffectedRow())
                                    .put("error", "Not enough mana to use hero's ability.");
                            ok = 0;
                        } else if (Hero2.isHasAttacked()) {
                            output.addObject().put("command", action.getCommand())
                                    .put("affectedRow", action.getAffectedRow())
                                    .put("error", "Hero has already attacked this turn.");
                            ok = 0;
                        } else if ((Hero2.getName().equals("Lord Royce"))
                                || (Hero2.getName().equals("Empress Thorina"))) {
                            if ((action.getAffectedRow() == 0) || (action.getAffectedRow() == 1)) {
                                output.addObject().put("command", action.getCommand())
                                        .put("affectedRow", action.getAffectedRow())
                                      .put("error", "Selected row does not belong to the enemy.");
                                ok = 0;
                            }

                            if (ok == 1) {
                                if (Hero2.getName().equals("Lord Royce")) {
                                    int mana = player2.getMana() - Hero2.getMana();
                                    player2.setMana(mana);
                                    LordRoyce lord = new LordRoyce(Hero2);
                                    lord.subzero(table, action.getAffectedRow());
                                    lord.setHasAttacked(true);
                                    Hero2 = new Hero(lord);
                                    table.table();
                                } else if (Hero2.getName().equals("Empress Thorina")) {
                                    EmpressThorina thorina = new EmpressThorina(Hero2);
                                    thorina.lowblow(table, action.getAffectedRow());
                                    player2.setMana(player2.getMana() - thorina.getMana());
                                    thorina.setHasAttacked(true);
                                    Hero2 = new Hero(thorina);
                                    table.table();
                                }
                            }


                        } else if ((Hero2.getName().equals("General Kocioraw"))
                                || (Hero2.getName().equals("King Mudface"))) {
                            if ((action.getAffectedRow() == 2)
                                    || (action.getAffectedRow() == trei)) {
                                output.addObject().put("command", action.getCommand())
                                        .put("affectedRow", action.getAffectedRow())
                                        .put("error",
                                           "Selected row does not belong to the current player.");
                                ok = 0;
                            }

                            if (ok == 1) {
                                if (Hero2.getName().equals("General Kocioraw")) {
                                    GeneralKocioraw general = new GeneralKocioraw(Hero2);
                                    general.bloodthirst(table, action.getAffectedRow());
                                    player2.setMana(player2.getMana() - general.getMana());
                                    general.setHasAttacked(true);
                                    Hero2 = new Hero(general);
                                    table.table();
                                } else if (Hero2.getName().equals("King Mudface")) {
                                    KingMudface king = new KingMudface(Hero2);
                                    king.earthborn(table, action.getAffectedRow());
                                    player2.setMana(player2.getMana() - king.getMana());
                                    king.setHasAttacked(true);
                                    Hero2 = new Hero(king);
                                    table.table();

                                }
                            }


                        }

                    }

                } else if (action.getCommand().equals("getTotalGamesPlayed")) {
                    output.addObject().put("command", action.getCommand())
                            .putPOJO("output", wins1 + wins2);
                } else if (action.getCommand().equals("getPlayerTwoWins")) {
                    output.addObject().put("command", action.getCommand())
                            .putPOJO("output", wins2);
                } else if (action.getCommand().equals("getPlayerOneWins")) {
                    output.addObject().put("command", action.getCommand())
                            .putPOJO("output", wins1);

                }

            }
        }


        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(filePath2), output);


    }
}
