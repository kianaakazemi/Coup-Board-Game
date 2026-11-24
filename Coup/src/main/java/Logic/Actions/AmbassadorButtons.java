package Logic.Actions;

import Gui.Board;
import Logic.Card.Card;
import Logic.Card.CardNames;
import Logic.Game.EventWriter;
import Logic.Game.Game;
import Logic.Players.Bot;
import Logic.Players.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AmbassadorButtons {
    public static List<String> cardOptions = new ArrayList<>();

    public static Card randomCard1;
    public static Card randomCard2;

    public static void blockStealing(Player player1,Player player2){
        Board.event.setText("Current action: " + player1.getIndex()  + " -> " + player2.getIndex() + " : Block stealing");
        Game.actionType = null;
        if (Game.findParanoid() != null) {
            Bot.paranoidChallenge(Game.findParanoid(),player1);
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(player1.isAllowed()) {
            Game.isStealingBlocked = true;
            EventWriter.writeBlockStealing(player1,player2);
        }else{
            Card card1 = player1.getCards().get(0);
            Card card2 = player1.getCards().get(1);

            boolean isAmbassador1 = card1.getCardName() == CardNames.Ambassador1 || card1.getCardName() == CardNames.Ambassador2 || card1.getCardName() == CardNames.Ambassador3;
            boolean isAmbassador2 = card2.getCardName() == CardNames.Ambassador1 || card2.getCardName() == CardNames.Ambassador2 || card2.getCardName() == CardNames.Ambassador3;
            Random randomizer = new Random();
            Card random = Game.cardList.getList().get(randomizer.nextInt(Game.cardList.getList().size()));

            if ((!card1.isFace() && isAmbassador1)){
                Game.isStealingBlocked = true;
                Game.cardList.getList().remove(random);
                player1.getCards().remove(card1);
                player1.getCards().add(0,random);
                GeneralButtons.swapCard(Game.challengeLauncher);
                EventWriter.writeBlockStealing(player1,player2);

            }else if((!card2.isFace() && isAmbassador2)) {
                Game.isStealingBlocked = true;
                Game.cardList.getList().remove(random);
                player1.getCards().remove(card2);
                player1.getCards().add(random);
                GeneralButtons.swapCard(Game.challengeLauncher);
                EventWriter.writeBlockStealing(player1,player2);

            }else{

                if (!card1.isFace()) {
                    card1.setFace(true);
                }else if (!card2.isFace()) {
                    card2.setFace(true);
                }
            }
        }

        player1.setAllowed(true);
        Board.rearrangePlayerPanels();
    }

    public static void showOptions(Player player){
        if (!player.isLoser()) {
            Random randomizer = new Random();
            Card random1 = Game.cardList.getList().get(randomizer.nextInt(Game.cardList.getList().size()));
            Game.cardList.getList().remove(random1);
            randomCard1 = random1;
            if (!player.getCards().get(0).isFace() && !player.getCards().get(0).isFace()) {
                Card random2 = Game.cardList.getList().get(randomizer.nextInt(Game.cardList.getList().size()));
                Game.cardList.getList().remove(random2);
                randomCard2 = random2;
                make4CardsOptionsList(random1,random2,player.getCards().get(0),player.getCards().get(1));

            }else{
                if (!player.getCards().get(0).isFace()) {
                    make2CardsOptionsList(random1,player.getCards().get(0));
                } else if (!player.getCards().get(1).isFace()) {
                    make2CardsOptionsList(random1,player.getCards().get(1));
                }
            }
        }
    }

    public static void exchange(Player player){
        Board.event.setText("Current action: " + player.getIndex()  + " : Exchange");
        if (!player.isLoser()) {
            Game.actionType = null;
            if (Game.findParanoid() != null) {
                Bot.paranoidChallenge(Game.findParanoid(),player);
            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (player.isAllowed()) {
                doTheExchange(player);
                EventWriter.writeExchange(player);
            }else{
                Card card1 = player.getCards().get(0);
                Card card2 = player.getCards().get(1);

                boolean isAmbassador1 = card1.getCardName() == CardNames.Ambassador1 || card1.getCardName() == CardNames.Ambassador2 || card1.getCardName() == CardNames.Ambassador3;
                boolean isAmbassador2 = card2.getCardName() == CardNames.Ambassador1 || card2.getCardName() == CardNames.Ambassador2 || card2.getCardName() == CardNames.Ambassador3;

                if ((!card1.isFace() && isAmbassador1) || (!card2.isFace() && isAmbassador2)){
                    doTheExchange(player);
                    GeneralButtons.swapCard(Game.challengeLauncher);
                    EventWriter.writeExchange(player);

                }else{
                    if (!card1.isFace()) {
                        card1.setFace(true);
                    }else if (!card2.isFace()) {
                        card2.setFace(true);
                    }
                }
            }
        }
        player.setAllowed(true);
        Board.rearrangePlayerPanels();
    }

    public static void doTheExchange(Player player){
        if (!player.getCards().get(0).isFace() && !player.getCards().get(0).isFace()) {
            if (Board.exchangeCards.getSelectedItem().equals(cardOptions.get(0))) {
                Game.cardList.getList().add(player.getCards().get(0));
                Game.cardList.getList().add(player.getCards().get(1));
                player.getCards().clear();
                player.getCards().add(randomCard1);
                player.getCards().add(randomCard2);
            } else if (Board.exchangeCards.getSelectedItem().equals(cardOptions.get(1))) {
                Game.cardList.getList().add(player.getCards().get(1));
                Game.cardList.getList().add(randomCard2);
                player.getCards().remove(player.getCards().get(1));
                player.getCards().add(randomCard1);
            } else if (Board.exchangeCards.getSelectedItem().equals(cardOptions.get(2))) {
                Game.cardList.getList().add(player.getCards().get(0));
                Game.cardList.getList().add(randomCard2);
                player.getCards().remove(player.getCards().get(0));
                player.getCards().add(randomCard1);
            } else if (Board.exchangeCards.getSelectedItem().equals(cardOptions.get(3))) {
                Game.cardList.getList().add(player.getCards().get(1));
                Game.cardList.getList().add(randomCard1);
                player.getCards().remove(player.getCards().get(1));
                player.getCards().add(0, randomCard2);
            } else if (Board.exchangeCards.getSelectedItem().equals(cardOptions.get(4))) {
                Game.cardList.getList().add(player.getCards().get(0));
                Game.cardList.getList().add(randomCard1);
                player.getCards().remove(player.getCards().get(0));
                player.getCards().add(0, randomCard2);
            }
        } else {
            if (!player.getCards().get(0).isFace()) {
                if (Board.exchangeCards.getSelectedItem().equals(cardOptions.get(0))) {
                    Game.cardList.getList().add(player.getCards().get(0));
                    player.getCards().remove(player.getCards().get(0));
                    player.getCards().add(0, randomCard1);
                }
            } else if (!player.getCards().get(1).isFace()) {
                if (Board.exchangeCards.getSelectedItem().equals(cardOptions.get(0))) {
                    Game.cardList.getList().add(player.getCards().get(1));
                    player.getCards().remove(player.getCards().get(1));
                    player.getCards().add(randomCard1);
                }
            }
        }
    }

    public static void make4CardsOptionsList(Card card1,Card card2,Card card3,Card card4){
        cardOptions.add(card1.getCardName() + " - " + card2.getCardName());
        cardOptions.add(card1.getCardName() + " - " + card3.getCardName());
        cardOptions.add(card1.getCardName() + " - " + card4.getCardName());
        cardOptions.add(card2.getCardName() + " - " + card3.getCardName());
        cardOptions.add(card2.getCardName() + " - " + card4.getCardName());
        cardOptions.add(card3.getCardName() + " - " + card4.getCardName());
    }

    public static void make2CardsOptionsList(Card card1, Card card2){
        cardOptions.add(String.valueOf(card1.getCardName()));
        cardOptions.add(String.valueOf(card2.getCardName()));
    }
}
