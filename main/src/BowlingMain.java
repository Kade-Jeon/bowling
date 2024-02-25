import Service.BowlingServiceImpl;
import etc.GSS;
import etc.Messages;
import etc.Player;

import java.util.*;

public class BowlingMain {

    public static void main(String[] args) {
        Messages msg = new Messages();

        BowlingServiceImpl bowlingService = new BowlingServiceImpl();
        msg.introImage();
        List<Player> playerList = bowlingService.insertPlayers();
        msg.showScoreboard(playerList);
        bowlingService.playGame(playerList);

    }


}