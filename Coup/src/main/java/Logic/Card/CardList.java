package Logic.Card;

import java.util.ArrayList;
import java.util.List;

public class CardList {
    private List<Card> list = new ArrayList<>();

    public CardList(){
        for (CardNames c : CardNames.values()) {
            list.add(new Card(c));
        }
    }

    public List<Card> getList() {
        return list;
    }

    public void setList(List<Card> list) {
        this.list = list;
    }
}
