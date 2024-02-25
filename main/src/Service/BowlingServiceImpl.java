package Service;

import Exceptions.BounsInputException;
import Exceptions.FirstInputException;
import Exceptions.SpareInputException;
import etc.GSS;
import etc.Messages;
import etc.Player;
import etc.Scoreboard;


import java.util.*;

public class BowlingServiceImpl {
    private final Scanner scanner = new Scanner(System.in);
    private final Messages msg = new Messages();

    public List<Player> insertPlayers() {
        while (true) {
            msg.askPeople();
            String players = scanner.nextLine();
            List<Player> playerList;
            switch (players) {
                case "1", "2", "3", "4":
                    return makePlyerList(players);
                default:
                    msg.askInputAgain();
            }
        }
    }

    public List<Player> makePlyerList(String playersNumber) {
        int players = Integer.parseInt(playersNumber);
        List<Player> playersList = new ArrayList<>(players);
        for (int i = 1; i <= players; i++) {
            Player player = new Player("Player" + i, new Scoreboard());
            playersList.add(player);
        }
        return playersList;
    }

    public void playGame(List<Player> playerList) {
        for (int i = 0; i < 10; i++) {
            msg.showRound(i + 1);
            for (Player player : playerList) {
                System.out.print(player.getName()+" ");
                player.showScoreboard();
            }
            for (Player player : playerList) {
                String first;
                String spare = null;
                while (true) {
                    System.out.print(player.getName()+" 샷 결과 : ");
                    first = scanner.nextLine();
                    try {
                        if (Integer.parseInt(first) > 10) { // STRIKE 입력 오류
                            throw new FirstInputException("샷 결과는 10을 초과할 수 없습니다.");
                        }
                        if (first.equals("10") && i != 9) { //STRIKE 성공, 1~9 라운드
                            player.getScoreboard().setScore(i, GSS.STRIKE.getSign());
                            break;
                        }
                        if (first.equals("10") && i == 9) { //STRIKE 성공, 10 라운드
                            break;
                        }
                        if (Integer.parseInt(first) < 10) { // STRIKE 실패
                            break;
                        }
                    } catch (FirstInputException e) {
                        System.out.println(e.getMessage());
                    }

                }//while - first

                while (true) {
                    if (first.equals("10") && i != 9) { // 1~9 라운드까지는 첫 투구가 스트라이크면 다음 투구 없음.
                        break;
                    }
                    System.out.print(player.getName()+" 스페어 결과 : ");
                    spare = scanner.nextLine();
                    try {
                        if (Integer.parseInt(first) + Integer.parseInt(spare) > 10 && i != 9) { //SPARE 입력 오류
                            throw new SpareInputException("스페어 입력 값이 잘못되었습니다." + (10 - Integer.parseInt(first)) + " 이하의 값만 입력 가능합니다.");
                        }
                        if (Integer.parseInt(first) + Integer.parseInt(spare) == 10 && i != 9) { // SPARE 성공
                            player.getScoreboard().setScore(i, first, GSS.SPARE.getSign());
                            break;
                        }

                        player.getScoreboard().setScore(i, first, spare);
                        break;

                    } catch (SpareInputException e) {
                        System.out.println(e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("스페어 값은 숫자만 입력 가능합니다.");
                    }
                }//while - spare

                if (i == 9 && (first.equals("10") || (Integer.parseInt(first) + Integer.parseInt(spare) == 10))) {
                    while (true) {
                        System.out.print(player.getName()+" 보너스 샷 결과 : ");
                        String bonus = scanner.nextLine();
                        try {
                            if (Integer.parseInt(bonus) > 10) { // 보너스 샷 입력 오류
                                throw new BounsInputException("보너스 샷 값 입력이 잘못되었습니다. 10 이하의 값만 입력 가능합니다.");
                            }
                            if (first.equals("10") && spare.equals("10") && bonus.equals("10")) { // X X X
                                player.getScoreboard().setScore(9, GSS.STRIKE.getSign(), GSS.STRIKE.getSign(), GSS.STRIKE.getSign());
                            }
                            if (first.equals("10") && (Integer.parseInt(spare) + Integer.parseInt(bonus) == 10)) { // X 샷 스페어
                                player.getScoreboard().setScore(9, GSS.STRIKE.getSign(), spare, GSS.SPARE.getSign());
                                break;
                            }
                            if (first.equals("10") && (Integer.parseInt(spare) + Integer.parseInt(bonus) != 10)) { // X 샷 스페어실패
                                player.getScoreboard().setScore(9, GSS.STRIKE.getSign(), spare, bonus);
                                break;
                            }
                            if ((Integer.parseInt(first) + Integer.parseInt(spare) == 10) && bonus.equals("10")) { // 샷 스페어 X
                                player.getScoreboard().setScore(9, first, spare, GSS.STRIKE.getSign());
                                break;
                            }
                            break;
                        } catch (BounsInputException e) {
                            System.out.println(e.getMessage());
                        } catch (NumberFormatException e) {
                            System.out.println("보너스 샷 값은 숫자만 입력 가능합니다.");
                        }
                    }
                }
                System.out.print(player.getName() + " ");
                player.showScoreboard();
                System.out.print(player.getName() + " ");
                scoreCalculator(i, playerList);
            }//for - player

        }//for - round
    }//playGame()

    public void scoreCalculator(int round, List<Player> playerList) {
        for (Player player : playerList) {
            List<Integer> scores = player.getScores();
            List<List<String>> scoreboard = player.getScoreboard().getScoreboard();

            /*
            * 스페어 - 00
            * 더블 - 000
            *
            * */

            if (scoreboard.get(round).get(0).equals("X")) {

            }

            int firstFirst = changeScore(scoreboard.get(round).get(0));
            int firstSecond = changeScore(scoreboard.get(round).get(1));

            if (firstFirst + firstSecond < 10) { // 초구 스페어실패
                scores.add(round, firstFirst + firstSecond);
            }

            if (firstFirst + firstFirst == 10) { // 초구 스페어성공
                scores.add(round, 00);
            }




            /*if (scoreboard.get(round).get(0).equals("X")) { // 스트라이크 (1번) + 초구
                int secondFirst = changeScore(scoreboard.get(round + 1).get(0));
                scores.add(round, 00);

                if (scoreboard.get(round + 1).get(0).equals("X")) { // 더블(스트라이크 연속 2번) + 초구
                    int thirdFirst = changeScore(scoreboard.get(round + 1 + 1).get(0));
                    scores.add(round, 20 + thirdFirst);

                    if (scoreboard.get(round + 1 + 1).get(0).equals("X")) { //터키 (스트라이크 연속 3번)
                        scores.add(round, 30);
                    }
                }
            }*/



            System.out.println(player.getScores());
        }
    }

    public int changeScore(String string) {
        if (string.equals("/")) {
            return -10;
        }
        if (string.equals("X")) {
            return 10;
        }
        if (string.equals("-")) {
            return 0;
        }


        if (string.equals("0")) {
            return 0;
        }
        if (string.equals("1")) {
            return 1;
        }
        if (string.equals("2")) {
            return 2;
        }
        if (string.equals("3")) {
            return 3;
        }
        if (string.equals("4")) {
            return 4;
        }
        if (string.equals("5")) {
            return 5;
        }
        if (string.equals("6")) {
            return 6;
        }
        if (string.equals("7")) {
            return 7;
        }
        if (string.equals("8")) {
            return 8;
        }
        if (string.equals("9")) {
            return 1;
        }
        return 0;
    }
}

