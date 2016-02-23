package blackjack;

import blackjack.dtos.Hand;
import blackjack.dtos.PlayStatus;

public interface GameAssessor {

    public PlayStatus assessRound(final Hand dealerHand, final Hand playerHand);
    
    public PlayStatus getStatus();
}
