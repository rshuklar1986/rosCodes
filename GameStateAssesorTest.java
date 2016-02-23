package blackjack;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GameStateAssesorTest {
    private BlackJack game;
    private GameAssessorImpl assessor;

    @Before
    public void setUp() {
        assessor = new GameAssessorImpl();
        game = new BlackJackImpl(assessor);
    }
    
    @Test
    public void assessRound_returns_true_isDealerBust_if_over_21() {
        game.deal(9, 8);
        game.deal(10, 1);
        game.deal(3, 5);
        assertTrue(assessor.getStatus().isDealerBust());
    }

    @Test
    public void assessRound_returns_true_isPlayerBust_if_over_21() {
        game.deal(8, 9);
        game.deal(1, 10);
        game.deal(2, 5);
        assertTrue(assessor.getStatus().isPlayerBust());
    }

    @Test
    public void assessRound_returns_true_isDealerPlaying_if_under_21() {
        game.deal(9, 8);
        assertTrue(assessor.getStatus().isPlaying());
    }

    @Test
    public void assessRound_returns_true_isDealerBlackJack_if_black_jack() {
        game.deal(12, 2);
        game.deal(1, 3);
        assertTrue(assessor.getStatus().isDealerBlackJack());
    }

    @Test
    public void assessRound_returns_true_isDealerBlackJack_if_different_order_black_jack() {
        game.deal(1, 2);
        game.deal(12, 3);
        assertTrue(assessor.getStatus().isDealerBlackJack());
    }

    @Test
    public void assessRound_returns_true_isPlayerBlackJack_if_blackJack() {
        game.deal(2, 12);
        game.deal(3, 1);
        assertTrue(assessor.getStatus().isPlayerBlackJack());
    }

    @Test
    public void assessRound_returns_true_isPlayerBlackJack_if_different_order_blackJack() {
        game.deal(2, 1);
        game.deal(3, 12);
        assertTrue(assessor.getStatus().isPlayerBlackJack());
    }
    
    @Test
    public void assessRound_returns_false_isPlayerBlackJack_if_ten_value_is_not_picture_card() {
        game.deal(2, 1);
        game.deal(3, 10);
        assertFalse(assessor.getStatus().isPlayerBlackJack());
    }
    
    @Test
    public void assessRound_returns_false_isDealerBlackJack_if_ten_value_is_not_picture_card() {
        game.deal(1, 2);
        game.deal(10, 3);
        assertFalse(assessor.getStatus().isDealerBlackJack());
    }

    @Test
    public void assessRound_returns_correct_state_when_used_after_each_deal() {
        game.deal(2, 1);
        game.deal(11, 5);
        game.deal(10, 3);
        assertFalse(assessor.getStatus().isDealerBlackJack());
        assertFalse(assessor.getStatus().isPlayerBlackJack());

        assertFalse(assessor.getStatus().isPlaying());

        assertTrue(assessor.getStatus().isDealerBust());
        assertFalse(assessor.getStatus().isPlayerBust());
    }
    
    @Test
    public void assessRound_returns_both_dealer_and_player_bust_when_both_over_21() {
        game.deal(13, 8);
        game.deal(5, 7);
        game.deal(9, 10);
        assertTrue(assessor.getStatus().isDealerBust());
        assertTrue(assessor.getStatus().isPlayerBust());
    }
}
