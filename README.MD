This is a test library for a job application

The idea is to create a Live footballworld cup score board as a library that can be used later in an application
I need to use TDD and thread safety as a main approach here. 

Plan for development goes as follows: 

1. setup and initialize a maven project
2. write interfaces
3. write test cases 
4. develop core functionality
5. enhance to be thread-safe and add extra test cases in case i see something is not covered

Usage: 

after adding the dependency, you can create a score board with

ScoreBoard board = new ScoreBoard();

The newly created scoreboard implements following methods:

    1.Match startMatch(Team var1, Team var2);

creates a match inside the scoreboard with 0,0 as starting score. 

Example: 

    board.startMatch(new Team("Spain"), new Team("Portugal");

 *** 

    2. void updateMatch(Match var1, Score var2);

updates an existing match with a score. example

board.updateMatch(someMatch, new Score(1,0));

***

    3. void finishMatch(Match var1);

finalizes the match and removes it from the board. Example: 

    board.finishMatch(someMatch);

***

    4. List<Match> getSummary();
    
retuns the list of active matches in order of creation. Example:

    Match match = board.getSummary().getFirst();

 this can be used to get one of match objects from the list

TODO: I think later maybe a 
Match getMatch(TeamPairing pairing) 
method needs to be implemented in order to make the process of getting corect match easier?


