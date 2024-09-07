package si.arstan.scoreboard;

import java.time.Instant;

public record Match(Team homeTeam, Team awayTeam, Score currentScore, Instant startTime) { }

