package blackjack.dtos;

import java.util.HashMap;
import java.util.Map;

public class Cards {
    Map<Integer, Card> map = new HashMap<Integer, Card>();
    public Cards() {
        this.map = buildMap();
    }

    public Map<Integer, Card> buildMap() {
        map.put(0, new Card(0, "No Card"));
        map.put(1, new Card(11, "Ace"));
        map.put(2, new Card(2, "Two"));
        map.put(3, new Card(3, "Three"));
        map.put(4, new Card(4, "Four"));
        map.put(5, new Card(5, "Five"));
        map.put(6, new Card(6, "Six"));
        map.put(7, new Card(7, "Seven"));
        map.put(8, new Card(8, "Eight"));
        map.put(9, new Card(9, "Nine"));
        map.put(10, new Card(10, "Ten"));
        map.put(11, new Card(10, "Jack"));
        map.put(12, new Card(10, "Queen"));
        map.put(13, new Card(10, "King"));
        map.put(14, new Card(11, "Ace"));
        map.put(15, new Card(2, "Two"));
        map.put(16, new Card(3, "Three"));
        map.put(17, new Card(4, "Four"));
        map.put(18, new Card(5, "Five"));
        map.put(19, new Card(6, "Six"));
        map.put(20, new Card(7, "Seven"));
        map.put(21, new Card(8, "Eight"));
        map.put(22, new Card(9, "Nine"));
        map.put(23, new Card(10, "Ten"));
        map.put(24, new Card(10, "Jack"));
        map.put(25, new Card(10, "Queen"));
        map.put(26, new Card(10, "King"));
        return map;
    }

    public Card getCard(int cardRef) {
        Card card = map.get(cardRef);
        if (card != null && card.getValue() != 0) {
            map.remove(cardRef);
        }
        return card;
    }
}