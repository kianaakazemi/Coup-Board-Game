package Logic.Game;

import Gui.GameEventWriter;
import Logic.Players.Player;

public class EventWriter {

    public static void writeIncome(Player player){
        Game.events += "\n" + player.getIndex() + " : Income";
    }

    public static void writeForeignAid(Player player){
        Game.events += "\n" + player.getIndex() + " : Foreign aid";
    }

    public static void writeCoup(Player player1,Player player2){
        Game.events += "\n" + player1.getIndex() + " -> " + player2.getIndex() + " : Coup";
    }

    public static void writeBlockAssassination(Player player1,Player player2){
        Game.events += "\n" + player1.getIndex() + " -> " + player2.getIndex() + " : Block assassination";
    }

    public static void writeExchangeWithOneCoin(Player player1){
        Game.events += "\n" + player1.getIndex() + " : Exchange card by paying one coin";
    }

    public static void writeChallenge(Player player1,Player player2){
        Game.events += "\n" + player1.getIndex() + " -> " + player2.getIndex() + " : Challenge";
    }

    public static void writeTax(Player player1){
        Game.events += "\n" + player1.getIndex() + " : Tax";
    }

    public static void writeBlockForeignAid(Player player1,Player player2){
        Game.events += "\n" + player1.getIndex() + " -> " + player2.getIndex() + " : Block foreign aid";
    }

    public static void writeSteal(Player player1,Player player2){
        Game.events += "\n" + player1.getIndex() + " -> " + player2.getIndex() + " : Steal";
    }

    public static void writeBlockStealing(Player player1,Player player2){
        Game.events += "\n" + player1.getIndex() + " -> " + player2.getIndex() + " : Block stealing";
    }

    public static void writeAssassination(Player player1,Player player2){
        Game.events += "\n" + player1.getIndex() + " -> " + player2.getIndex() + " : Assassination";
    }

    public static void writeExchange(Player player1){
        Game.events += "\n" + player1.getIndex() + " : Exchange";
    }

    public static void writeExchangeAssassinWithAmbassador(Player player1){
        Game.events += "\n" + player1.getIndex() + " : Exchange assassin with ambassador";
    }

    public static void writeLoser(Player player1){
        Game.events += "\n" + player1.getIndex() + " LOST!";
    }

    public static void writeWinner(Player player1){
        Game.events += "\n" + player1.getIndex() + " WON!";
    }

}
