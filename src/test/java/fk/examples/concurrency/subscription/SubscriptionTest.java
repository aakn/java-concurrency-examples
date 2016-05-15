package fk.examples.concurrency.subscription;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubscriptionTest {

  ExecutorService executorService;

  @Before
  public void setUp() throws Exception {
    executorService = Executors.newCachedThreadPool();
  }

  @Test
  public void shouldNotConsumeCorrectly() throws Exception {
    Buffer buffer = new SimpleBuffer();
    executorService.execute(new Producer(buffer));
    executorService.execute(new Consumer(buffer));


    executorService.shutdown();
    executorService.awaitTermination(1L, TimeUnit.MINUTES);
    log.info("Both consumer and producer are done");
  }
}