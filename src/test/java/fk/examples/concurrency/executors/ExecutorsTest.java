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
    executorService.execute(new PrintRunnable("task 1"));
    executorService.execute(new PrintRunnable("task 2"));
    executorService.execute(new PrintRunnable("task 3"));

    executorService.shutdown();
    executorService.awaitTermination(10L, TimeUnit.SECONDS);
    log.info("done with the test");
  }
}
