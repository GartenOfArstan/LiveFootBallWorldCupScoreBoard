package si.arstan.scoreboard;

import org.junit.jupiter.api.Test;

import java.util.List;

public class SummaryTest {

    @Test
    public void summaryCountTest()
    {

        ScoreBoard board = new ScoreBoard();

        board.startMatch(new Team("Spain"), new Team("Portugal"));
        Match match = board.startMatch(new Team("Italy"), new Team("Greece"));
        board.startMatch(new Team("Slovenia"), new Team("Austria"));

        board.finishMatch(match);

        List<Match> summary = board.getSummary();
        Integer expectedResult = 2;

        assert expectedResult.equals(summary.size());

    }


    @Test
    public void summaryOrderTest()
    {

        ScoreBoard board = new ScoreBoard();

        board.startMatch(new Team("Spain"), new Team("Portugal"));
        Match match = board.startMatch(new Team("Italy"), new Team("Greece"));
        board.startMatch(new Team("Slovenia"), new Team("Austria"));

        board.finishMatch(match);

        List<Match> summary = board.getSummary();

        Match earliest = summary.getFirst();
        Match latest = summary.getLast();

        assert earliest.homeTeam().equals(new Team("Spain"));
        assert earliest.awayTeam().equals(new Team("Portugal"));

        assert latest.homeTeam().equals(new Team("Slovenia"));
        assert latest.awayTeam().equals(new Team("Austria"));

    }
}
