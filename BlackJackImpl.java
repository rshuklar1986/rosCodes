package blackjack;

import blackjack.dtos.Card;
import blackjack.dtos.Cards;
import blackjack.dtos.Hand;
import blackjack.dtos.PlayStatus;

public class BlackJackImpl implements BlackJack {
    private static final String PLAYER = "Player";
    private static final String DEALER = "Dealer";

    private GameAssessorImpl assessor;
    private Hand dealerHand;
    private Hand playerHand;
    private Cards cards;

    public BlackJackImpl(GameAssessorImpl assessor) {
        this.cards = new Cards();
        this.dealerHand = new Hand();
        this.playerHand = new Hand();
        this.assessor = assessor;
    }

    @Override
    public boolean deal(final int dealerCardRef, final int playerCardRef) {
        final Card dealerCard = cards.getCard(dealerCardRef);
        final Card playerCard = cards.getCard(playerCardRef);

        final Hand dealer = dealCard(dealerCard, DEALER);
        final Hand player = dealCard(playerCard, PLAYER);

        final PlayStatus thisRound = assessor.assessRound(dealer, player);
        return thisRound.isPlaying();
    }

    @Override
    public Hand getDealerHand() {
        return dealerHand;
    }

    @Override
    public Hand getPlayerHand() {
        return playerHand;
    }

    @Override
    public String showDown() {
        if (assessor.getStatus().isDealerBust() && assessor.getStatus().isPlayerBust()) {
            return "Draw";
        }
        
        else if (assessor.getStatus().isPlayerBlackJack() || assessor.getStatus().isDealerBust()
                || (playerHand.getTotal() > dealerHand.getTotal() && !assessor.getStatus().isPlayerBust())) {
            return "You win!";
        }
        return "You lose!";
    }

    private Hand dealCard(final Card card, final String target) {
        switch (target) {
        case DEALER:
            if (card != null) {
                setDealerHand(card);
                setDealerScore(card);
                return dealerHand;
            }
        case PLAYER:
            if (card != null) {
                setPlayerHand(card);
                setPlayerScore(card);
                return playerHand;
            }
        }
        return new Hand();
    }

    private void setDealerHand(final Card dealerCard) {
        dealerHand.setHand(dealerCard);
    }

    private void setPlayerHand(final Card playerCard) {
        playerHand.setHand(playerCard);
    }

    private void setDealerScore(final Card dealerCard) {
        dealerHand.setTotal(dealerCard.getValue());
    }

    private void setPlayerScore(final Card playerCard) {
        playerHand.setTotal(playerCard.getValue());
    }
}
