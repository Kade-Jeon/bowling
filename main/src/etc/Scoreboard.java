package etc;

import Exceptions.SpareInputException;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {

    private List<List<String>> scoreboard = new ArrayList<>();


    public Scoreboard() {
        for (int i = 0; i < 10; i++) {
            scoreboard.add(i, new ArrayList<>(2));
            if (i == 9) {
                scoreboard.add(i, new ArrayList<>(3));
            }
        }
    }

    public void setScore(int round, String first) {
        scoreboard.add(round, List.of(first));
    }

    public void setScore(int round, String first, String spare) {
        scoreboard.add(round, List.of(first, spare));
    }

    public void setScore(int round, String first, String spare, String bonus) {
        scoreboard.add(round, List.of(first, spare, bonus));
    }

    public List<List<String>> getScoreboard() {
        return scoreboard;
    }
}
