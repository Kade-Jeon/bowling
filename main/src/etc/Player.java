package etc;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private Scoreboard scoreboard;
    private List<String> scores;
    public Player(String name, Scoreboard scoreboard) {
        this.name = name;
        this.scoreboard = scoreboard;
        this.scores = new ArrayList<>(10);
        initScores();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public void setScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    public List<String> getScores() {
        return scores;
    }

    public int getRoundScore(int round) {
        return Integer.parseInt(scores.get(round));
    }

    public void setScores(List<String> scores) {
        this.scores = scores;
    }

    public void initScores() {
        for (int i = 0; i < 10; i++) {
            scores.add(i,"");
        }
    }

    public void showScoreboard() {
        System.out.println(scoreboard.getScoreboard().subList(0, 10));
    }

}
