package si.arstan.scoreboard;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 *  Simple tests to see basic functionality
 */

public class SimpleScoreBoardTest
{

    @Test
    public void testScoreBoardInit()
    {

        ScoreBoard scoreBoard = new ScoreBoard();

        assert scoreBoard.getSummary().isEmpty() : "new scoreboard should be empty";

    }

    @ParameterizedTest
    @MethodSource("generateTeamPairForStart")
    public void testStartMatch(Team homeTeam, Team awayTeam, Integer result)
    {

        ScoreBoard scoreBoard = new ScoreBoard();

        scoreBoard.startMatch(homeTeam,awayTeam);

        assert result.equals(scoreBoard.getSummary().size()) : "scoreboard should have one match";

    }

    @ParameterizedTest
    @MethodSource("generateTeamPairForFinish")
    public void testFinishMatch(Team homeTeam, Team awayTeam, Integer result)
    {

        ScoreBoard scoreBoard = new ScoreBoard();

        scoreBoard.startMatch(homeTeam,awayTeam);

        Match match = scoreBoard.getSummary().getFirst();

        scoreBoard.finishMatch(match);

        assert result.equals(scoreBoard.getSummary().size()) : "scoreboard should have 0 matches";

    }


    @ParameterizedTest
    @MethodSource("generateTeamPairAndScore")
    public void testUpdateMatch(Team homeTeam, Team awayTeam, Score score, Score result)
    {

        ScoreBoard scoreBoard = new ScoreBoard();

        scoreBoard.startMatch(homeTeam,awayTeam);

        Match match = scoreBoard.getSummary().getFirst();

        scoreBoard.updateMatch(match, score);

        assert result.equals(scoreBoard.getSummary().getFirst().currentScore()) : "score for match " + match + " should be "+result;

    }

    protected static Stream<Arguments>  generateTeamPairForStart()
    {
        return Stream.of(
                    Arguments.of(new Team("Spain"), new Team("Italy"), 1),
                    Arguments.of(new Team("Greece"), new Team("Slovenia"), 1)
                );

    }

    protected static Stream<Arguments>  generateTeamPairForFinish()
    {
        return Stream.of(
                Arguments.of(new Team("Spain"), new Team("Italy"), 0),
                Arguments.of(new Team("Greece"), new Team("Slovenia"), 0)
        );

    }

    protected static Stream<Arguments>  generateTeamPairAndScore()
    {
        return Stream.of(
                Arguments.of(new Team("Spain"), new Team("Italy"), new Score(1,1),new Score(1,1)),
                Arguments.of(new Team("Greece"), new Team("Slovenia"), new Score(1,3), new Score(1,3))
        );

    }

}
