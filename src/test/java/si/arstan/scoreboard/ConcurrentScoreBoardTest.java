package si.arstan.scoreboard;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.random.RandomGenerator;
import java.util.stream.IntStream;

public class ConcurrentScoreBoardTest {

    private static final Integer NUMBER_OF_THREADS = 50;

    @Test
    public void concurrentScoreUpdateTest() throws InterruptedException {

        ScoreBoard scoreBoard = new ScoreBoard();

        scoreBoard.startMatch(new Team("Spain"), new Team("Portugal"));

        Match match = scoreBoard.getSummary().getFirst();

        RandomGenerator generator = RandomGenerator.JumpableGenerator.of("value");

        ExecutorService service = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        CountDownLatch latch = new CountDownLatch(NUMBER_OF_THREADS);

        for (int i=0; i<NUMBER_OF_THREADS ; i++)
        {
            service.execute(() -> {

                scoreBoard.updateMatch(match, new Score(generator.nextInt(10), generator.nextInt(10) ));
                latch.countDown();

            });
        }

        latch.await();

    }


}
