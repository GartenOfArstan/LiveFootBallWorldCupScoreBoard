package si.arstan.scoreboard;

import java.util.List;

public interface IScoreBoard
{

    Match startMatch(Team homeTeam, Team awayTeam);
    void updateMatch(Match match, Score score);
    void finishMatch(Match match);
    List<Match> getSummary();

}
