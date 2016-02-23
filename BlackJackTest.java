package blackjack;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import blackjack.dtos.PlayStatus;

public class BlackJackTest {
    private BlackJack game;
    private GameAssessorImpl assessor;

    @Before
    public void setUp() {
        assessor = new GameAssessorImpl();
        game = new BlackJackImpl(assessor);
    }

    @Test
    public void deal_deals_cards_according_to_random_number_given() {
        game.deal(2, 0);
        assertEquals("Two", game.getDealerHand().getHand().get(0).getName());
        assertEquals(2, game.getDealerHand().getHand().get(0).getValue());
    }

    @Test
    public void deal_deals_cards_according_to_random_multiple_numbers_given() {
        game.deal(2, 6);
        assertEquals("Two", game.getDealerHand().getHand().get(0).getName());
        assertEquals(2, game.getDealerHand().getHand().get(0).getValue());

        assertEquals("Six", game.getPlayerHand().getHand().get(0).getName());
        assertEquals(6, game.getPlayerHand().getHand().get(0).getValue());
    }

    @Test
    public void deal_deals_in_correct_order_to_house_and_player() {
        game.deal(5, 2);
        game.deal(3, 4);

        assertEquals("Five", game.getDealerHand().getHand().get(0).getName());
        assertEquals(5, game.getDealerHand().getHand().get(0).getValue());

        assertEquals("Three", game.getDealerHand().getHand().get(1).getName());
        assertEquals(3, game.getDealerHand().getHand().get(1).getValue());

        assertEquals("Two", game.getPlayerHand().getHand().get(0).getName());
        assertEquals(2, game.getPlayerHand().getHand().get(0).getValue());

        assertEquals("Four", game.getPlayerHand().getHand().get(1).getName());
        assertEquals(4, game.getPlayerHand().getHand().get(1).getValue());
    }

    @Test
    public void deal_stops_dealing_for_dealer_but_carries_on_for_player() {
        game.deal(3, 2);
        game.deal(0, 4);
        game.deal(0, 10);
        assertEquals(3, game.getDealerHand().getTotal());
        assertEquals(16, game.getPlayerHand().getTotal());
    }

    @Test
    public void deal_stops_dealing_for_player_but_carries_on_for_dealer() {
        game.deal(3, 2);
        game.deal(4, 0);
        game.deal(10, 0);
        assertEquals(17, game.getDealerHand().getTotal());
        assertEquals(2, game.getPlayerHand().getTotal());
    }

    @Test
    public void deal_does_not_deal_same_card_twice() {
        game.deal(2, 2);
        assertEquals("Two", game.getDealerHand().getHand().get(0).getName());
        assertEquals(2, game.getDealerHand().getHand().get(0).getValue());
        assertEquals(1, game.getDealerHand().getHand().size());
    }

    @Test
    public void deal_handles_picture_cards() {
        game.deal(11, 12);

        assertEquals("Jack", game.getDealerHand().getHand().get(0).getName());
        assertEquals(10, game.getDealerHand().getHand().get(0).getValue());

        assertEquals("Queen", game.getPlayerHand().getHand().get(0).getName());
        assertEquals(10, game.getPlayerHand().getHand().get(0).getValue());
    }
    
    @Test
    public void deal_handles_zero_reference_number_for_dealer_when_no_card_deal() {
        game.deal(3, 4);
        game.deal(0, 1);
        assertEquals(3, game.getDealerHand().getTotal());
        assertEquals(1, game.getDealerHand().getHand().size());
    }
    
    @Test
    public void deal_handles_zero_reference_number_for_player_when_no_card_deal() {
        game.deal(3, 4);
        game.deal(10, 0);
        assertEquals(4, game.getPlayerHand().getTotal());
        assertEquals(1, game.getPlayerHand().getHand().size());
    }

    @Test
    public void getDealerScore_returns_score_1_card() {
        game.deal(12, 0);
        assertEquals(10, game.getDealerHand().getTotal());
    }

    @Test
    public void getPlayerScore_returns_score_1_card() {
        game.deal(0, 12);
        assertEquals(10, game.getPlayerHand().getTotal());
    }

    @Test
    public void getDealerScore_returns_score_multiple_card() {
        game.deal(0, 5);
        game.deal(0, 3);
        assertEquals(8, game.getPlayerHand().getTotal());
    }

    @Test
    public void getPlayerScore_returns_score_multiple_card() {
        game.deal(5, 0);
        game.deal(3, 0);
        assertEquals(8, game.getDealerHand().getTotal());
    }
    
    @Test
    public void deal_returns_true_if_still_playing() {
        assertTrue(game.deal(5, 0));
    }
    
    @Test
    public void deal_returns_false_if_not_still_playing() {
        game.deal(10, 13);
        game.deal(8, 3);
        assertFalse(game.deal(5, 0));
    }

    @Test
    public void showDown_returns_winning_message_if_player_wins_when_both_playing() {
        game.deal(3, 4);
        game.deal(6, 10);
        PlayStatus status = assessor.getStatus();
        assertTrue(status.isPlaying());
        assertEquals("You win!", game.showDown());
    }
    
    @Test
    public void showDown_returns_winning_message_if_player_wins_when_dealer_bust() {
        game.deal(10, 11);
        game.deal(12, 4);
        game.deal(5, 6);
        PlayStatus status = assessor.getStatus();
        assertTrue(status.isDealerBust());
        assertEquals("You win!", game.showDown());
    }
    
    @Test
    public void showDown_returns_winning_message_if_player_wins_when_blackJack() {
        game.deal(10, 11);
        game.deal(7, 1);
        game.deal(4, 0);
        PlayStatus status = assessor.getStatus();
        assertTrue(status.isPlayerBlackJack());
        assertEquals("You win!", game.showDown());
    }
    
    @Test
    public void showDown_returns_losing_message_if_player_loses_when_both_playing() {
        game.deal(8, 4);
        game.deal(9, 10);
        assertEquals("You lose!", game.showDown());
    }
    
    @Test
    public void showDown_returns_losing_message_if_player_loses_when_player_bust() {
        game.deal(8, 11);
        game.deal(5, 10);
        game.deal(2, 4);
        PlayStatus status = assessor.getStatus();
        assertTrue(status.isPlayerBust());
        assertEquals("You lose!", game.showDown());
    }
    
    @Test
    public void showDown_returns_losing_message_if_player_loses_when_dealer_blackJack() {
        game.deal(13, 8);
        game.deal(1, 7);
        game.deal(0, 6);
        PlayStatus status = assessor.getStatus();
        assertTrue(status.isDealerBlackJack());
        assertEquals("You lose!", game.showDown());
    }
    
    @Test
    public void showDown_returns_losing_message_if_player_draws_with_dealer_when_normal_deal() {
        game.deal(6, 3);
        game.deal(1, 4);
        assertEquals("You lose!", game.showDown());
    }
    
    @Test
    public void showDown_returns_draw_message_if_player_and_dealer_both_lose_normal_deal() {
        game.deal(13, 8);
        game.deal(5, 7);
        game.deal(9, 10);
        PlayStatus status = assessor.getStatus();
        assertTrue(status.isDealerBust());
        assertTrue(status.isPlayerBust());
        assertEquals("Draw", game.showDown());
    }
    
    @Test
    public void showDown_returns_losing_message_if_player_draws_with_dealer_when_blackJack_deal() {
        game.deal(13, 26);
        game.deal(1, 14);
        assertEquals("You lose!", game.showDown());
    }
    
    
}
