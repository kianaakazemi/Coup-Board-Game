import Gui.Start;
import Logic.Game.Game;


public class GuiMain {
    public static void main(String[] args) throws  InterruptedException {
        new Start();
        while (true) {
            System.out.print("");
            if (Start.run) {
                Game.runGame();
                break;
            }
        }

    }
}
