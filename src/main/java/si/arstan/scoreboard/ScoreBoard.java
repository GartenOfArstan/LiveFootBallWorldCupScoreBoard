package si.arstan.scoreboard;

import java.util.List;

public class ScoreBoard implements IScoreBoard{

    @Override
    public void startMatch(Team homeTeam, Team awayTeam) {

    }

    @Override
    public void updateMatch(Match match, Score score) {

    }

    @Override
    public void finishMatch(Match match) {

    }

    @Override
    public List<Match> getSummary() {
        return List.of();
    }
}