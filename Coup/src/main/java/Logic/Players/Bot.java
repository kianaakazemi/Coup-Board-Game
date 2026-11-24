package Logic.Players;

import Gui.Board;
import Logic.Actions.DukeButtons;
import Logic.Actions.GeneralButtons;
import Logic.Actions.NotChallengingActionsType;
import Logic.Card.Card;
import Logic.Card.CardNames;
import Logic.Game.EventWriter;
import Logic.Game.Game;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class Bot extends Player{
    private BotType botType;

    public Bot(List<Card> cards, Index index,BotType botType) {
        super(cards, index);
        this.botType = botType;
    }

    public static void coupMaker(Player player){
        if (player.getCoins() < 7) {
            DukeButtons.tax(player);
        }else{
            Player p = Game.shufflePlayer(player);
            GeneralButtons.coup(player,p);
        }
        Board.rearrangePlayerPanels();
    }



    public static void cautiousKiller(Player player){
        Card card1 = player.getCards().get(0);
        Card card2 = player.getCards().get(1);

        boolean isAssassin1 = card1.getCardName() == CardNames.Assassin1 || card1.getCardName() == CardNames.Assassin2 || card1.getCardName() == CardNames.Assassin3;
        boolean isAssassin2 = card2.getCardName() == CardNames.Assassin1 || card2.getCardName() == CardNames.Assassin2 || card2.getCardName() == CardNames.Assassin3;

        boolean isAmbassador1 = card1.getCardName() == CardNames.Ambassador1 || card1.getCardName() == CardNames.Ambassador2 || card1.getCardName() == CardNames.Ambassador3;
        boolean isAmbassador2 = card2.getCardName() == CardNames.Ambassador1 || card2.getCardName() == CardNames.Ambassador2 || card2.getCardName() == CardNames.Ambassador3;

        if(!((!card1.isFace() && isAssassin1) || (!card2.isFace() && isAssassin2))){

            if((!card1.isFace() && isAmbassador1)){
                Board.event.setText("Current action: " + player.getIndex() + " : Exchange");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                exchangeTwoCards(player,card1);

                if (!player.isAllowed()) {
                    GeneralButtons.swapCard(Game.challengeLauncher);
                }

            }else if (!card2.isFace() && isAmbassador2){
                Board.event.setText("Current action: " + player.getIndex() + " : Exchange");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                exchangeTwoCards(player,card2);

                if (!player.isAllowed()) {
                    GeneralButtons.swapCard(Game.challengeLauncher);
                }

             }else{
                if (player.getCoins() == 1) {
                    exchangeWithOneCoin(player);
                }else if(player.getCoins() == 0){
                    GeneralButtons.foreignAid(player);
                }else{
                    GeneralButtons.income(player);
                }
            }
        } else if (!player.isLoser()) {
            GeneralButtons.income(player);
        }
        player.setAllowed(true);
        Board.rearrangePlayerPanels();
    }


    public static void saver(Player player){
        GeneralButtons.foreignAid(player);
    }


    public static void paranoid(Player player){
        GeneralButtons.income(player);
    }


    public static void paranoidChallenge(Player player1,Player player2){
        System.out.println(Game.actionType);
        if (Game.actionType != NotChallengingActionsType.coup && Game.actionType != NotChallengingActionsType.foreignAid && Game.actionType != NotChallengingActionsType.income && Game.actionType != NotChallengingActionsType.exchangeWithOneCoin) {
            if (Game.canLaunchChallenge) {
                Board.event.setText("Current action: " + player1.getIndex() + " -> " + player2.getIndex() +" : Challenge");
                player2.setAllowed(false);
                Game.challengeLauncher = player1;
                EventWriter.writeChallenge(player1,player2);
            }
            Game.canLaunchChallenge = !Game.canLaunchChallenge;
        }else{
            Game.actionType = null;
        }
    }


    public static void exchangeWithOneCoin(Player player){
        Game.actionType = NotChallengingActionsType.exchangeWithOneCoin;
        Board.event.setText("Current action: " + player.getIndex() + " : Exchange by paying one coin");
        if (!player.isLoser()) {
            player.setCoins(player.getCoins() - 1);
            Random randomizer = new Random();
            Card random = Game.cardList.getList().get(randomizer.nextInt(Game.cardList.getList().size()));
            Game.cardList.getList().remove(random);
            if (!player.getCards().get(0).isFace()) {
                player.getCards().remove(0);
            } else if (!player.getCards().get(1).isFace()) {
                player.getCards().remove(1);
            }
            player.getCards().add(random);
            EventWriter.writeExchangeWithOneCoin(player);
        }
    }


    public static void exchangeTwoCards(Player player,Card card){
        if (!player.isLoser()) {
            Random randomizer = new Random();
            Card random1 = Game.cardList.getList().get(randomizer.nextInt(Game.cardList.getList().size()));
            Game.cardList.getList().remove(random1);
            Card random2 = Game.cardList.getList().get(randomizer.nextInt(Game.cardList.getList().size()));
            Game.cardList.getList().remove(random2);
            if (random1.getCardName() == CardNames.Assassin1 || random1.getCardName() == CardNames.Assassin2 || random1.getCardName() == CardNames.Assassin3) {
                player.getCards().remove(card);
                player.getCards().add(random1);
                Game.cardList.getList().add(random2);
                EventWriter.writeExchangeAssassinWithAmbassador(player);
            } else if (random2.getCardName() == CardNames.Assassin1 || random2.getCardName() == CardNames.Assassin2 || random2.getCardName() == CardNames.Assassin3) {
                player.getCards().remove(card);
                player.getCards().add(random2);
                Game.cardList.getList().add(random1);
                EventWriter.writeExchangeAssassinWithAmbassador(player);
            } else {
                Game.cardList.getList().add(random1);
                Game.cardList.getList().add(random2);
            }
        }
    }



    public BotType getBotType() {
        return botType;
    }

    public void setBotType(BotType botType) {
        this.botType = botType;
    }
}
