package blackjack.dtos;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> hand = new ArrayList<Card>();
    private int total;

    public Hand() {
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(Card card) {
        if (card.getValue() != 0) {
            hand.add(card);
        }
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total += total;
    }
}
