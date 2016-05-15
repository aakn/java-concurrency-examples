package fk.examples.concurrency.sort;

import org.junit.BeforeClass;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParallelSortTest {

  static int[] numbers;

  @BeforeClass
  public static void setUpOnce() throws Exception {
    numbers = new Random().ints(15_000_000).toArray();
  }

  @Test
  public void shouldSortNormally() throws Exception {
    log.info("Starting the sort now");
    int[] clone = numbers.clone();

    Instant now = Instant.now();
    Arrays.sort(numbers);
    log.info("normal sort done in {}", Duration.between(now, Instant.now()).toMillis());
  }


  @Test
  public void shouldSortParallely() throws Exception {
    log.info("Starting the parallel sort now {}");
    int[] clone = numbers.clone();

    Instant now = Instant.now();
    Arrays.parallelSort(clone);
    log.info("normal sort done in {}", Duration.between(now, Instant.now()).toMillis());
  }
}
