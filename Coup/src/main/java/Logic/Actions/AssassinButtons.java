package Logic.Actions;

import Gui.Board;
import Logic.Card.Card;
import Logic.Card.CardNames;
import Logic.Game.EventWriter;
import Logic.Game.Game;
import Logic.Players.Bot;
import Logic.Players.Player;

import java.util.Random;

public class AssassinButtons {

    public static void assassinate(Player player1, Player player2){
        if (player1.getCoins() >= 3 ) {
            if (!player2.isLoser()) {
                Board.event.setText("Current action: " + player1.getIndex()  + " -> " + player2.getIndex() + " : Assassinate");
                Game.actionType = null;
                if (Game.findParanoid() != null) {
                    Bot.paranoidChallenge(Game.findParanoid(),player1);
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (player1.isAllowed() && !Game.isAssassinationBlocked) {
                    GeneralButtons.swapCard(player2);
                    player1.setCoins(player1.getCoins() - 3);
                    EventWriter.writeAssassination(player1,player2);

                } else if (!player1.isAllowed() && !Game.isAssassinationBlocked) {
                    Card card1 = player1.getCards().get(0);
                    Card card2 = player1.getCards().get(1);

                    boolean isAssassin1 = card1.getCardName() == CardNames.Assassin1 || card1.getCardName() == CardNames.Assassin2 || card1.getCardName() == CardNames.Assassin3;
                    boolean isAssassin2 = card2.getCardName() == CardNames.Assassin1 || card2.getCardName() == CardNames.Assassin2 || card2.getCardName() == CardNames.Assassin3;
                    Random randomizer = new Random();
                    Card random = Game.cardList.getList().get(randomizer.nextInt(Game.cardList.getList().size()));

                    if ((!card1.isFace() && isAssassin1)) {
                        GeneralButtons.swapCard(player2);
                        player1.setCoins(player1.getCoins() - 3);
                        Game.cardList.getList().remove(random);
                        player1.getCards().remove(card1);
                        player1.getCards().add(0, random);
                        GeneralButtons.swapCard(Game.challengeLauncher);
                        EventWriter.writeAssassination(player1,player2);

                    } else if ((!card2.isFace() && isAssassin2)) {
                        GeneralButtons.swapCard(player2);
                        player1.setCoins(player1.getCoins() - 3);
                        Game.cardList.getList().remove(random);
                        player1.getCards().remove(card2);
                        player1.getCards().add(random);
                        GeneralButtons.swapCard(Game.challengeLauncher);
                        EventWriter.writeAssassination(player1,player2);

                    } else {

                        if (!card1.isFace()) {
                            card1.setFace(true);
                        } else if (!card2.isFace()) {
                            card2.setFace(true);
                        }
                    }
                }
                Game.isAssassinationBlocked = false;
                player1.setAllowed(true);
                Board.rearrangePlayerPanels();
                Game.clicked = true;
            }else {
                if (player1 instanceof Bot) {
                    assassinate(player1, Game.shufflePlayer(player1));
                }
            }
        }

    }

}
