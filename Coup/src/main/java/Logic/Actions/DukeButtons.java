package Logic.Actions;

import Gui.Board;
import Logic.Card.Card;
import Logic.Card.CardNames;
import Logic.Game.EventWriter;
import Logic.Game.Game;
import Logic.Players.Bot;
import Logic.Players.Player;

import java.util.Random;

public class DukeButtons {

    public static void tax(Player player){
        Board.event.setText("Current action: " + player.getIndex() + " : Tax");
        Game.actionType = null;
        if (Game.findParanoid() != null) {
            Bot.paranoidChallenge(Game.findParanoid(),player);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(player.isAllowed()) {
            player.setCoins(player.getCoins() + 3);
            EventWriter.writeTax(player);
        }else{
            Card card1 = player.getCards().get(0);
            Card card2 = player.getCards().get(1);

            boolean isDuke1 = card1.getCardName() == CardNames.Duke1 || card1.getCardName() == CardNames.Duke2 || card1.getCardName() == CardNames.Duke3;
            boolean isDuke2 = card2.getCardName() == CardNames.Duke1 || card2.getCardName() == CardNames.Duke2 || card2.getCardName() == CardNames.Duke3;
            Random randomizer = new Random();
            Card random = Game.cardList.getList().get(randomizer.nextInt(Game.cardList.getList().size()));

            if ((!card1.isFace() && isDuke1)){
                player.setCoins(player.getCoins() + 3);
                Game.cardList.getList().remove(random);
                player.getCards().remove(card1);
                player.getCards().add(0,random);
                GeneralButtons.swapCard(Game.challengeLauncher);
                EventWriter.writeTax(player);

            }else if((!card2.isFace() && isDuke2)) {
                player.setCoins(player.getCoins() + 3);
                Game.cardList.getList().remove(random);
                player.getCards().remove(card2);
                player.getCards().add(random);
                GeneralButtons.swapCard(Game.challengeLauncher);
                EventWriter.writeTax(player);

            }else{

                if (!card1.isFace()) {
                    card1.setFace(true);
                }else if (!card2.isFace()) {
                    card2.setFace(true);
                }
            }
        }
        player.setAllowed(true);
        Board.rearrangePlayerPanels();
    }


    public static void blockForeignAid(Player player1,Player player2){
        Board.event.setText("Current action: " + player1.getIndex()  + " -> " + player2.getIndex() + " : Block foreign aid");
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
            player2.setAllowed(false);
            EventWriter.writeBlockForeignAid(player1,player2);
        }else{
            Card card1 = player1.getCards().get(0);
            Card card2 = player1.getCards().get(1);

            boolean isDuke1 = card1.getCardName() == CardNames.Duke1 || card1.getCardName() == CardNames.Duke2 || card1.getCardName() == CardNames.Duke3;
            boolean isDuke2 = card2.getCardName() == CardNames.Duke1 || card2.getCardName() == CardNames.Duke2 || card2.getCardName() == CardNames.Duke3;
            Random randomizer = new Random();
            Card random = Game.cardList.getList().get(randomizer.nextInt(Game.cardList.getList().size()));

            if ((!card1.isFace() && isDuke1)){
                player2.setAllowed(false);
                Game.cardList.getList().remove(random);
                player1.getCards().remove(card1);
                player1.getCards().add(0,random);
                GeneralButtons.swapCard(Game.challengeLauncher);
                EventWriter.writeBlockForeignAid(player1,player2);

            }else if((!card2.isFace() && isDuke2)) {
                player2.setAllowed(false);
                Game.cardList.getList().remove(random);
                player1.getCards().remove(card2);
                player1.getCards().add(random);
                GeneralButtons.swapCard(Game.challengeLauncher);
                EventWriter.writeBlockForeignAid(player1,player2);

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
