package fk.examples.concurrency.subscription;

import java.util.Random;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Producer implements Runnable {

  private final Buffer sharedBuffer;

  private final Random random = new Random();

  public Producer(Buffer sharedBuffer) {
    this.sharedBuffer = sharedBuffer;
  }

  @Override
  public void run() {
    int total = 0;

    for (int i = 0; i <= 10; i++) {
      try {
        Thread.sleep(random.nextInt(1000));
        sharedBuffer.blockingPut(i);
      } catch (InterruptedException e) {
        log.error("interrupt", e);
      }
      total += i;
      log.info("Produced: \t{}\t sum: {}", i, total);

    }

    log.info("Total produced: {}", total);

  }
}
