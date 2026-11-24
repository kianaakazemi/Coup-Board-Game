package Logic.Game;

import Gui.Board;
import Gui.GameEventWriter;
import Logic.Actions.GeneralButtons;
import Logic.Actions.NotChallengingActionsType;
import Logic.Card.Card;
import Logic.Card.CardList;
import Logic.Players.Bot;
import Logic.Players.BotType;
import Logic.Players.Human;
import Logic.Players.Player;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    public static int turn = 1;

    public static List<Player> players;

    public static CardList cardList;

    Gson gson = new Gson();

    private static Game game;

    public static Player challengeLauncher;

    public static boolean isStealingBlocked = false;
    public static boolean isAssassinationBlocked = false;
    public static boolean clicked = false;
    public static boolean canLaunchChallenge = true;

    public static NotChallengingActionsType actionType;

    public static String events = "";

    public static Game getInstance() throws FileNotFoundException {
        if (game==null){
            game = new Game();
        }
        return game;
    }


    public Game() throws FileNotFoundException {
        players = new ArrayList<>();
        cardList = new CardList();
        addPlayersToList();
    }

    public void addPlayersToList() throws FileNotFoundException {
        File humanFile = new File("./src/main/java/Files/Human.json");
        Human human = gson.fromJson(new JsonReader(new FileReader(humanFile)),Human.class);
        players.add(human);

        for (int i = 1; i < 4; i++) {
            File botFile = new File("./src/main/java/Files/Bot" + i + ".json");
            Bot bot = gson.fromJson(new JsonReader(new FileReader(botFile)),Bot.class);
            players.add(bot);
        }

        for (Player p : players) {
            for (int i = 0; i < cardList.getList().size(); i++) {
                Card c = cardList.getList().get(i);
                if (p.getCards().get(0).equals(c)) {
                    cardList.getList().remove(c);
                }
                if (p.getCards().get(1).equals(c)) {
                    cardList.getList().remove(c);
                }
            }
        }
    }

    public static void runGame() throws InterruptedException {
       if (turn % 4 == 1 && players.get(0).isLoser()) {
           clicked = true;
           turn++;
       }
        if (!whichPlayer().isLoser()) {
            Board.turn.setText("Turn: " + fillTurnLabel());
        }
        Thread.sleep(5000);

        while (!isFinished()) {
            if (clicked) {
                switch (turn % 4) {
                    case 2:
                        if (!players.get(1).isLoser()) {
                            if (players.get(1).getCoins() >= 10) {
                                GeneralButtons.coup(players.get(1),shufflePlayer(players.get(1)));
                            } else {
                                whichBotToRun(1);
                            }
                            Thread.sleep(5000);
                        }
                        break;
                    case 3:
                        if (!players.get(2).isLoser()) {
                            if (players.get(2).getCoins() >= 10) {
                                GeneralButtons.coup(players.get(2),shufflePlayer(players.get(2)));
                            } else {
                                whichBotToRun(2);
                            }
                            Thread.sleep(5000);
                        }
                        break;
                    case 0:
                        if (!players.get(3).isLoser()) {
                            if (players.get(3).getCoins() >= 10) {
                                GeneralButtons.coup(players.get(3),shufflePlayer(players.get(3)));
                            } else {
                                whichBotToRun(3);
                            }
                            Thread.sleep(3000);
                        }
                        clicked = false;
                        break;
                }
                checkLosers();
                Board.rearrangePlayerPanels();
                if (!isFinished()) {
                    turn++;
                }
                if (!whichPlayer().isLoser()) {
                    Board.turn.setText("Turn: " + fillTurnLabel());
                }

            } else {
                runGame();
            }
        }
        for (Player p : players) {
            if (!p.isLoser()) {
                Board.event.setText(p.getIndex() + " WON!");
                Thread.sleep(2000);
                EventWriter.writeWinner(p);
            }
        }
    }


    public static void checkLosers() throws InterruptedException {
        for (Player p : players) {
            if (p.getCards().get(0).isFace() && p.getCards().get(1).isFace() && !p.isLoser()) {
                p.setLoser(true);
                Board.event.setText(p.getIndex() + " LOST!");
                Thread.sleep(2000);
                EventWriter.writeLoser(p);
                break;
            }
        }
    }


    public static Player findParanoid(){
        for (Player p : players) {
            if (p instanceof Bot && ((Bot) p).getBotType() == BotType.Paranoid && !p.isLoser()) {
                return p;
            }
        }
        return null;
    }



    public static String fillTurnLabel(){
        switch (turn % 4){
            case 1:
                return "You";
            case 2:
                return "Player 1";
            case 3:
                return "Player 2";
            case 0:
                return "Player 3";
        }
        return "";
    }

    public static Player whichPlayer(){
        switch (turn % 4){
            case 1:
                return players.get(0);
            case 2:
                return players.get(1);
            case 3:
                return players.get(2);
            case 0:
                return players.get(3);
        }
        return null;
    }

    public static void whichBotToRun(int botNumber){
        if (((Bot) players.get(botNumber)).getBotType() == BotType.CoupMaker) {
            Bot.coupMaker(players.get(botNumber));
        }else if (((Bot) players.get(botNumber)).getBotType() == BotType.CautiousKiller) {
            Bot.cautiousKiller(players.get(botNumber));
        }else if (((Bot) players.get(botNumber)).getBotType() == BotType.Paranoid) {
            Bot.paranoid(players.get(botNumber));
        }else if (((Bot) players.get(botNumber)).getBotType() == BotType.Saver) {
            Bot.saver(players.get(botNumber));
        }
    }

    public static boolean isFinished(){
        int counter = 0;
        for (Player p : players) {
            if (p.isLoser()) {
                counter++;
            }
        }
        return counter == 3;
    }

    public static Player shufflePlayer(Player player){
        List<Player> otherPlayers = new ArrayList<>();
        for (Player p : Game.players) {
            if (!(p.getIndex() == player.getIndex()) && !p.isLoser()) {
                otherPlayers.add(p);
            }
        }
        Random randomizer = new Random();
        return otherPlayers.get(randomizer.nextInt(otherPlayers.size()));
    }


    public List<Player> getPlayers() {
        return players;
    }

    public CardList getCardList() {
        return cardList;
    }

    public void setCardList(CardList cardList) {
        Game.cardList = cardList;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        Game.turn = turn;
    }
}
