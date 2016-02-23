package blackjack.dtos;

public class PlayStatus {
    private boolean dealerBust;
    private boolean playerBust;
    private boolean playing = true;
    private boolean dealerBlackJack;
    private boolean playerBlackJack;
    
    public boolean isDealerBust() {
        return dealerBust;
    }

    public boolean isPlayerBust() {
        return playerBust;
    }

    public boolean isPlaying() {
        return playing;
    }

    public boolean isDealerBlackJack() {
        return dealerBlackJack;
    }

    public boolean isPlayerBlackJack() {
        return playerBlackJack;
    }
    
    public void setDealerBust(boolean bust) {
        this.dealerBust = bust;
    }

    public void setPlayerBust(boolean bust) {
        this.playerBust = bust;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public void setDealerBlackJack(boolean blackJack) {
        this.dealerBlackJack = blackJack;
    }

    public void setPlayerBlackJack(boolean blackJack) {
        this.playerBlackJack = blackJack;
    }

}
