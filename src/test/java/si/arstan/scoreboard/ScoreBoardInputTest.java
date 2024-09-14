package si.arstan.scoreboard;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ScoreBoardInputTest {

    @Test
    public void addingExistingMatch()
    {

        assertThrows(IllegalArgumentException.class,
                ()->{
                    ScoreBoard board = new ScoreBoard();

                    board.startMatch(new Team("Portugal"), new Team("Spain"));

                    board.startMatch(new Team("Portugal"), new Team("Spain"));
                });

    }

    @Test
    public void closeExistingMatchTwice()
    {

        assertThrows(IllegalArgumentException.class,
                ()->{

                    ScoreBoard board = new ScoreBoard();

                    Match match = board.startMatch(new Team("Portugal"), new Team("Spain"));

                    board.finishMatch(match);

                    board.finishMatch(match);

                });

    }


    @ParameterizedTest
    @MethodSource("generateWrongScores")
    public void addWrongScore(Score score)
    {

        ScoreBoard board = new ScoreBoard();

        Match match = board.startMatch(new Team("Portugal"), new Team("Spain"));

        assertThrows(IllegalArgumentException.class,
                ()->{
                    board.updateMatch(match, score);
                }
        );
    }

    protected static Stream<Arguments> generateWrongScores()
    {
        return Stream.of(
                Arguments.of(new Score(-1,-1)),
                Arguments.of(new Score(0,-5)),
                Arguments.of(new Score(-2,0)),
                Arguments.of(new Score(5,null))
        );

    }
}
