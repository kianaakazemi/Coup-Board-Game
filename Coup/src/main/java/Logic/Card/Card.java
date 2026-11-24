package Logic.Card;

public class Card {
    private CardNames cardName;
    private boolean isFace;

    public Card(CardNames cardName) {
        this.cardName = cardName;
        isFace = false;
    }

    public CardNames getCardName() {
        return cardName;
    }

    public void setCardName(CardNames cardName) {
        this.cardName = cardName;
    }

    public boolean isFace() {
        return isFace;
    }

    public void setFace(boolean face) {
        isFace = face;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardName=" + cardName +
                ", isFace=" + isFace +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return isFace == card.isFace && cardName == card.cardName;
    }

}
