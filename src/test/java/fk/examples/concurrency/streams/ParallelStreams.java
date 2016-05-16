package fk.examples.concurrency.streams;

import org.junit.Test;

import java.util.Random;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParallelStreams {

  @Test
  public void shouldRunSequentially() throws Exception {
    new Random().ints(1, 100)
        .map(this::doWork)
        .peek(i -> log.info("peeking sequentially {}", i))
        .limit(20)
        .count();
  }

  @Test
  public void shouldRunParallely() throws Exception {
    new Random().ints(1, 100)
        .parallel()
        .map(this::doWork)
        .peek(i -> log.info("peeking parallely {}", i))
        .limit(20)
        .count();
  }

  private int doWork(int i) {
    log.info("doing some with with {}", i);
    return i * i;
  }
}
