package fileio;

import java.io.Serializable;

public class Score implements Serializable {
    private int score;
    private String playerName;

    public Score(int score, String playerName) {
        this.score = score;
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
