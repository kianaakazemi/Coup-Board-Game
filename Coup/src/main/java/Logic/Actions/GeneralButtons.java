package Logic.Actions;

import Gui.Board;

import Logic.Card.Card;
import Logic.Card.CardNames;
import Logic.Game.EventWriter;
import Logic.Game.Game;
import Logic.Players.Bot;
import Logic.Players.Human;
import Logic.Players.Player;

import java.util.Random;


public class GeneralButtons {

    public static void income(Player player){
        Board.event.setText("Current action: " + player.getIndex() + " : Income");
        EventWriter.writeIncome(player);
        Game.actionType = NotChallengingActionsType.income;
        player.setCoins(player.getCoins() + 1);
        Board.rearrangePlayerPanels();
    }

    public static void foreignAid(Player player){
        Board.event.setText("Current action: " + player.getIndex() + " : Foreign aid");
        Game.actionType = NotChallengingActionsType.foreignAid;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        if(player.isAllowed()){
            player.setCoins(player.getCoins() + 2);
            EventWriter.writeForeignAid(player);
        }
        player.setAllowed(true);
        Board.rearrangePlayerPanels();
    }

    public static void coup(Player player1,Player player2){
        Game.actionType = NotChallengingActionsType.coup;
        if (player1.getCoins() >= 7) {
            if (!player2.isLoser()) {
                Board.event.setText("Current action: " + player1.getIndex() + " -> " + player2.getIndex() +" : Coup");
                if (player1 instanceof Human) {
                    Game.clicked = true;
                }
                if (player2 instanceof Bot) {
                    if (!player2.getCards().get(0).isFace()) {
                        player2.getCards().get(0).setFace(true);
                    } else if (!player2.getCards().get(1).isFace()) {
                        player2.getCards().get(1).setFace(true);
                    }

                } else if (player2 instanceof Human) {
                    swapCard(player2);
                }
                player1.setCoins(player1.getCoins() - 7);
                EventWriter.writeCoup(player1,player2);
            }
        }else{
            if (player1 instanceof Bot) {
                coup(player1, Game.shufflePlayer(player1));
            }
        }
        Board.rearrangePlayerPanels();
    }

    public static void blockAssassination(Player player1,Player player2){
        Board.event.setText("Current action: " + player1.getIndex() + " -> " + player2.getIndex() +" : Block assassination");
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
            Game.isAssassinationBlocked = true;
            EventWriter.writeBlockAssassination(player1,player2);
        }else{

            Card card1 = player1.getCards().get(0);
            Card card2 = player1.getCards().get(1);

            boolean isAssassin1 = card1.getCardName() == CardNames.Assassin1 || card1.getCardName() == CardNames.Assassin2 || card1.getCardName() == CardNames.Assassin3;
            boolean isAssassin2 = card2.getCardName() == CardNames.Assassin1 || card2.getCardName() == CardNames.Assassin2 || card2.getCardName() == CardNames.Assassin3;
            Random randomizer = new Random();
            Card random = Game.cardList.getList().get(randomizer.nextInt(Game.cardList.getList().size()));

            if ((!card1.isFace() && isAssassin1)){
                Game.isAssassinationBlocked = true;
                Game.cardList.getList().remove(random);
                player1.getCards().remove(card1);
                player1.getCards().add(0,random);
                GeneralButtons.swapCard(Game.challengeLauncher);
                EventWriter.writeBlockAssassination(player1,player2);

            }else if((!card2.isFace() && isAssassin2)) {
                Game.isAssassinationBlocked = true;
                Game.cardList.getList().remove(random);
                player1.getCards().remove(card2);
                player1.getCards().add(random);
                GeneralButtons.swapCard(Game.challengeLauncher);
                EventWriter.writeBlockAssassination(player1,player2);

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

    public static void swapCard(Player player){
        if (player instanceof Human) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (player.getCards().get(0).getCardName() == Board.selectedCard.getCardName()) {
                player.getCards().get(0).setFace(true);
            }else if (player.getCards().get(1).getCardName() == Board.selectedCard.getCardName()) {
                player.getCards().get(1).setFace(true);
            }
            Board.selectedCard = null;
            Board.selectedLabel.setBorder(null);
            Board.selectedLabel = null;
        }else{
            if (!player.getCards().get(0).isFace()) {
                player.getCards().get(0).setFace(true);
            }else if (!player.getCards().get(1).isFace()) {
                player.getCards().get(1).setFace(true);
            }
        }
        Board.rearrangePlayerPanels();

    }

    public static void exchangeWithOneCoin(Player player){
        Board.event.setText("Current action: " + player.getIndex() + " : Exchange by paying one coin");
        Game.actionType = NotChallengingActionsType.exchangeWithOneCoin;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!player.isLoser()) {
            if (player.getCoins() >= 1) {
                player.setCoins(player.getCoins() - 1);
                Random randomizer = new Random();
                Card random = Game.cardList.getList().get(randomizer.nextInt(Game.cardList.getList().size()));
                Game.cardList.getList().remove(random);
                if (player.getCards().get(0).getCardName() == Board.selectedCard.getCardName()) {
                    Game.cardList.getList().add(player.getCards().get(0));
                    player.getCards().remove(0);
                    player.getCards().add(0,random);
                }else if (player.getCards().get(1).getCardName() == Board.selectedCard.getCardName()) {
                    Game.cardList.getList().add(player.getCards().get(1));
                    player.getCards().remove(1);
                    player.getCards().add(random);
                }
                Board.selectedCard = null;
                EventWriter.writeExchangeWithOneCoin(player);
                Game.clicked = true;
            }
        }
        Board.rearrangePlayerPanels();

    }

    public static void launchChallenge(Player player){
        Board.event.setText("Current action: " + Game.players.get(0).getIndex() + " -> " + player.getIndex() +" : Challenge");
        player.setAllowed(false);
        Game.challengeLauncher = Game.players.get(0);
        EventWriter.writeChallenge(Game.players.get(0),player);

    }

}
