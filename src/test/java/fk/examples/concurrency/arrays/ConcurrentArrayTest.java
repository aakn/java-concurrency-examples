package fk.examples.concurrency.arrays;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcurrentArrayTest {

  SharedArray array;

  @Before
  public void setUp() throws Exception {
    array = new SharedArray(6);
  }

  @Test
  public void shouldNotWriteCorrectly() throws Exception {

    ExecutorService executorService = Executors.newCachedThreadPool();
    executorService.execute(new ArrayWriter(array, 1));
    executorService.execute(new ArrayWriter(array, 10));

    executorService.shutdown();
    executorService.awaitTermination(1L, TimeUnit.MINUTES);

    log.info("array state: {}", array);


  }
}