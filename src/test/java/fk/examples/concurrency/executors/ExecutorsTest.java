package fk.examples.concurrency.executors;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExecutorsTest {

  Random random;


  @Before
  public void setUp() throws Exception {
    random = new Random();
  }

  @Test
  public void shouldExecute() throws Exception {

    log.info("Starting the test");

    ExecutorService executorService = Executors.newCachedThreadPool();
    executorService.execute(getRunnable("task 1"));
    executorService.execute(getRunnable("task 2"));
    executorService.execute(getRunnable("task 3"));

    executorService.shutdown();
    executorService.awaitTermination(10L, TimeUnit.SECONDS);
  }

  private Runnable getRunnable(final String taskName) {
    return () -> {
      log.info("Starting {}", taskName);
      try {
        int millis = random.nextInt(3000);
        log.info("going to sleep for {} for task", millis, taskName);
        Thread.sleep(millis);
      } catch (InterruptedException e) {
        log.error("an exception", e);
      }
      log.info("done with {}", taskName);
    };
  }
}
