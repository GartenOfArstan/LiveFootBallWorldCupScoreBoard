package si.arstan.scoreboard;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ScoreBoard implements IScoreBoard{

    private final ConcurrentHashMap<TeamPairing, Match> matches = new ConcurrentHashMap<>();

    @Override
    public Match startMatch(Team homeTeam, Team awayTeam) {

        TeamPairing pairing = new TeamPairing(homeTeam, awayTeam);

        if (matches.containsKey(pairing)) {
            throw new IllegalArgumentException(STR."the pairing \{pairing} is already present");
        }

        Match newMatch = new Match(pairing.homeTeam(), pairing.awayTeam(), new Score(0,0), Instant.now());

        matches.put(pairing, newMatch);

        return newMatch;

    }

    @Override
    public void updateMatch(Match match, Score score) {

        TeamPairing pairing = new TeamPairing(match.homeTeam(), match.awayTeam());

        if (!matches.containsKey(pairing))
        {
            throw new IllegalArgumentException(STR."the pairing \{pairing} cannot be updated, not present");
        }

        if (score.homeScore()==null || score.homeScore()<0)
        {
            throw new IllegalArgumentException(STR."the score \{score} has uncorrect home score");
        }

        if (score.awayScore()==null || score.awayScore()<0)
        {
            throw new IllegalArgumentException(STR."the score \{score} has uncorrect away score");
        }

        Match beforeUpdate = matches.get(pairing);

        Match updatedMatch = new Match(pairing.homeTeam(), pairing.awayTeam(), score, beforeUpdate.startTime());

        matches.put(pairing, updatedMatch);

    }

    @Override
    public void finishMatch(Match match) {

        TeamPairing pairing = new TeamPairing(match.homeTeam(), match.awayTeam());

        if (!matches.containsKey(pairing))
        {
            throw new IllegalArgumentException(STR."the pairing \{pairing} cannot be finished, not present");
        }

        matches.remove(pairing);

    }

    @Override
    public List<Match> getSummary() {

        return matches.values().stream().sorted(Comparator.comparing(Match::startTime)).toList();

    }
}