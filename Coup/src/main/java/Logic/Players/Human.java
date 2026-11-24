package Logic.Players;

import Logic.Card.Card;

import java.util.List;

public class Human extends Player{

    public Human(List<Card> cards, Index index) {
        super(cards, index);
    }

    @Override
    public String toString() {
        return this.isLoser()+"";
    }
}
