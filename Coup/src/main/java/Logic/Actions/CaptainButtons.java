package Logic.Actions;

import Gui.Board;
import Logic.Card.Card;
import Logic.Card.CardNames;
import Logic.Game.EventWriter;
import Logic.Game.Game;
import Logic.Players.Bot;
import Logic.Players.Player;

import java.util.Random;

public class CaptainButtons {

    public static void steal(Player player1,Player player2){
        if (player2.getCoins() >= 2 && !player2.isLoser()) {
            Board.event.setText("Current action: " + player1.getIndex()  + " -> " + player2.getIndex() + " : Steal");
            Game.actionType = null;
            if (Game.findParanoid() != null) {
                Bot.paranoidChallenge(Game.findParanoid(),player1);
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (player1.isAllowed() && !Game.isStealingBlocked) {
                player2.setCoins(player2.getCoins() - 2);
                player1.setCoins(player1.getCoins() + 2);
                EventWriter.writeSteal(player1,player2);

            } else if (!player1.isAllowed() && !Game.isStealingBlocked) {
                Card card1 = player1.getCards().get(0);
                Card card2 = player1.getCards().get(1);

                boolean isCaptain1 = card1.getCardName() == CardNames.Captain1 || card1.getCardName() == CardNames.Captain2 || card1.getCardName() == CardNames.Captain3;
                boolean isCaptain2 = card2.getCardName() == CardNames.Captain1 || card2.getCardName() == CardNames.Captain2 || card2.getCardName() == CardNames.Captain3;
                Random randomizer = new Random();
                Card random = Game.cardList.getList().get(randomizer.nextInt(Game.cardList.getList().size()));

                if ((!card1.isFace() && isCaptain1)) {
                    player2.setCoins(player2.getCoins() - 2);
                    player1.setCoins(player1.getCoins() + 2);
                    Game.cardList.getList().remove(random);
                    player1.getCards().remove(card1);
                    player1.getCards().add(0, random);
                    GeneralButtons.swapCard(Game.challengeLauncher);
                    EventWriter.writeSteal(player1,player2);

                } else if ((!card2.isFace() && isCaptain2)) {
                    player2.setCoins(player2.getCoins() - 2);
                    player1.setCoins(player1.getCoins() + 2);
                    Game.cardList.getList().remove(random);
                    player1.getCards().remove(card2);
                    player1.getCards().add(random);
                    GeneralButtons.swapCard(Game.challengeLauncher);
                    EventWriter.writeSteal(player1,player2);

                } else {

                    if (!card1.isFace()) {
                        card1.setFace(true);
                    } else if (!card2.isFace()) {
                        card2.setFace(true);
                    }
                }
            }
            Game.isStealingBlocked = false;
            player1.setAllowed(true);
            Board.rearrangePlayerPanels();
            Game.clicked = true;
        }else{
            if (player1 instanceof Bot) {
                steal(player1, Game.shufflePlayer(player1));
            }
        }
    }

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

            boolean isCaptain1 = card1.getCardName() == CardNames.Captain1 || card1.getCardName() == CardNames.Captain2 || card1.getCardName() == CardNames.Captain3;
            boolean isCaptain2 = card2.getCardName() == CardNames.Captain1 || card2.getCardName() == CardNames.Captain2 || card2.getCardName() == CardNames.Captain3;
            Random randomizer = new Random();
            Card random = Game.cardList.getList().get(randomizer.nextInt(Game.cardList.getList().size()));

            if ((!card1.isFace() && isCaptain1)){
                Game.isStealingBlocked = true;
                Game.cardList.getList().remove(random);
                player1.getCards().remove(card1);
                player1.getCards().add(0,random);
                GeneralButtons.swapCard(Game.challengeLauncher);
                EventWriter.writeBlockStealing(player1,player2);

            }else if((!card2.isFace() && isCaptain2)) {
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
}
