package blackjack;

import blackjack.dtos.Hand;
import blackjack.dtos.PlayStatus;

public class GameAssessorImpl implements GameAssessor {
    private static final int ACE = 11;
    private static final int TEN = 10;
    private static final int TWENTY_ONE = 21;

    private PlayStatus status;

    public GameAssessorImpl() {
        this.status = new PlayStatus();
    }

    @Override
    public PlayStatus assessRound(final Hand dealerHand, final Hand playerHand) {
        boolean dealerBust = verifyIfDealerBust(dealerHand);
        boolean playerBust = verifyIfPlayerBust(playerHand);
        boolean blackJack = verifyIfBlackJack(dealerHand, playerHand);
        verifyIfPlaying(dealerBust, playerBust, blackJack);
        return status;
    }

    @Override
    public PlayStatus getStatus() {
        return status;
    }

    private boolean verifyIfBlackJack(final Hand dealerHand, final Hand playerHand) {
        if (dealerHand.getTotal() == TWENTY_ONE
                && (dealerCardCombo(dealerHand, 1, 0) || dealerCardCombo(dealerHand, 0, 1))) {
            status.setDealerBlackJack(true);
            return status.isDealerBlackJack();
        }

        if (playerHand.getTotal() == TWENTY_ONE
                && (playerCardCombo(playerHand, 1, 0) || playerCardCombo(playerHand, 0, 1))) {
            status.setPlayerBlackJack(true);
            return status.isPlayerBlackJack();
        }
        return false;
    }

    private void verifyIfPlaying(boolean dealerBust, boolean playerBust, boolean blackJack) {
        if (dealerBust || playerBust || blackJack ) {
            status.setPlaying(false);
        }
    }

    private boolean playerCardCombo(final Hand playerHand, final int positionA, final int positionB) {
        return !"Ten".equals(playerHand.getHand().get(positionB).getName())
                && playerHand.getHand().get(positionA).getValue() == ACE
                && playerHand.getHand().get(positionB).getValue() == TEN;
    }

    private boolean dealerCardCombo(final Hand dealerHand, final int positionA, final int positionB) {
        return !"Ten".equals(dealerHand.getHand().get(positionB).getName())
                && dealerHand.getHand().get(positionA).getValue() == ACE
                && dealerHand.getHand().get(positionB).getValue() == TEN;
    }

    private boolean verifyIfDealerBust(final Hand dealerHand) {
        if (dealerHand.getTotal() > TWENTY_ONE) {
            status.setDealerBust(true);
        }
        return status.isDealerBust();
    }

    private boolean verifyIfPlayerBust(final Hand playerHand) {
        if (playerHand.getTotal() > TWENTY_ONE) {
            status.setPlayerBust(true);
        }
        return status.isPlayerBust();
    }
}
