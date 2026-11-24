package Logic.Players;

import Logic.Card.Card;

import java.util.List;

public class Player {
    private List<Card> cards;
    private Index index;
    private int coins;
    private boolean isLoser;
    private boolean isAllowed;

    public Player( List<Card> cards, Index index) {
        this.cards = cards;
        this.index = index;
        coins = 2;
        isLoser = false;
        isAllowed = true;
    }


    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Index getIndex() {
        return index;
    }

    public void setIndex(Index index) {
        this.index = index;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public boolean isLoser() {
        return isLoser;
    }

    public void setLoser(boolean loser) {
        isLoser = loser;
    }

    public boolean isAllowed() {
        return isAllowed;
    }

    public void setAllowed(boolean allowed) {
        isAllowed = allowed;
    }

    @Override
    public String toString() {
        return  index + "";
    }
}
