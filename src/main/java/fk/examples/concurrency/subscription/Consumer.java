package fk.examples.concurrency.subscription;

import java.util.Random;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Consumer implements Runnable {

  private final Buffer sharedBuffer;

  private final Random random = new Random();

  public Consumer(Buffer sharedBuffer) {
    this.sharedBuffer = sharedBuffer;
  }

  @Override
  public void run() {
    int total = 0;

    for (int i = 0; i <= 10; i++) {
      try {
        Thread.sleep(random.nextInt(1000));
        int value = sharedBuffer.blockingGet();
        total += value;
        log.info("Consumed: \t{}\t sum: {}", value, total);
      } catch (InterruptedException e) {
        log.error("interrupt", e);
      }

    }

    log.info("Total consumed: {}", total);

  }
}
