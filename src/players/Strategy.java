package players;

public enum Strategy {
    TWO_P_OPTIMAL(0,0),
    FOUR_P_OPTIMAL(0,0);

    private int wins;
    private int gamesPlayed;

    Strategy(int wins, int gamesPlayed) {
        this.wins = wins;
        this.gamesPlayed = gamesPlayed;
    }

    public int getWins() {
        return wins;
    }

    public void addWin() {
        wins++;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void addPlay() {
        gamesPlayed++;
    }
}
