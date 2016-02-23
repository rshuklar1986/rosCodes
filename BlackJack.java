package blackjack;

import blackjack.dtos.Hand;

public interface BlackJack {
    
    public boolean deal(int dealerCardRef, int playerCardRef);

    public Hand getDealerHand();

    public Hand getPlayerHand();

    public String showDown();
}