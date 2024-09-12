package si.arstan.scoreboard;

/**
 * This record is only for use in Concurrent hash map as a key type.
 * It's kind of redundant to have Match and Pair both have the teams, but it is what it is
 * @param homeTeam
 * @param awayTeam
 */

public record TeamPairing(Team homeTeam, Team awayTeam) {
}
